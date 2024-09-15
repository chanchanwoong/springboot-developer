package me.chanwoong.springbootdeveloper.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class QuizControllerTest {

    protected MockMvc mockMvc;
    private final WebApplicationContext context;

    // 객체 직렬화를 위해 ObjectMapper 사용
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("quiz(): GET / quiz?code=1 이면 응답 코드는 201이며 응답 메시지는 message >>> Created를 반환해야 한다.")
    @Test
    public void getQuiz1() throws Exception {
        // given
        // 고정된 url 지정
        final String url = "/quiz";

        // when
        // requestParam code 값 1 지정
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "1")
        );

        // then
        // 응답 코드와 응답 메시지 확인
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("message >>> Created"));
    }

    @DisplayName("quiz(): GET / quiz?code=2 이면 응답 코드는 400이며 응답 메시지는 message >>> Bad Request를 반환해야 한다.")
    @Test
    public void getQuiz2() throws Exception {
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "2")
        );

        // then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("message >>> Bad Request"));
    }

    @DisplayName("quiz(): POST /quiz?code=1 이면 응답 코드는 403이며 응답 메시지는 message >>> Forbidden를 반환해야 한다.")
    @Test
    public void postQuiz1() throws Exception {
        // given
        final String url = "/quiz";

        // when
        // Code 라는 record 클래스를 requestBody에 JSON 형태로 입력
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1)))
        );

        // then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("message >>> Forbidden"));
    }

    @DisplayName("quiz(): POST /quiz?code=4 이면 응답 코드는 200 응답 메시지는 message >>> OK를 반환해야 한다.")
    @Test
    public void postQuiz2() throws Exception {
        // given
        final String url = "/quiz";

        // when
        // Code 라는 record 클래스를 requestBody에 JSON 형태로 입력
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(4)))
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("message >>> OK"));
    }
}