package me.chanwoong.springbootdeveloper.presentation;

import lombok.RequiredArgsConstructor;
import me.chanwoong.springbootdeveloper.domain.Member;
import me.chanwoong.springbootdeveloper.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class TestControllerTest {

    protected  MockMvc mockMvc;
    private final WebApplicationContext context;
    private final MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers : 리스트 형태로 전체 멤버 조회")
    @Test
    public void getAllMembers() throws Exception {
        // given
        // Member 엔티티 저장
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "박찬웅"));

        // when
        // 멤버 리스트 조회 API 호출
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        // 응답 코드 200 OK 이며, 반환 값 중 0번 째 요소의 id와 name이 저장된 값과 같은지 확인
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}