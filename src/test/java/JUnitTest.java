import org.junit.jupiter.api.*;

import java.io.*;

public class JUnitTest {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    @DisplayName("1 + 2 = 3 를 JUnit으로 테스트")
    @Test
    public void junitTest() throws IOException {
        // when
        int a = 1, b = 2, sum = 3;

        // when과 then이 동시에 있는 형태
        Assertions.assertEquals(a + b, sum);

        bw.write("1 + 2 = 3 를 JUnit으로 테스트 \n");
    }

    @DisplayName("1 + 2 = 4 를 JUnit으로 테스트")
    @Test
    public void junitFiledTest() throws IOException {
        // when
        int a = 1, b = 2, sum = 4;

        // when과 then이 동시에 있는 형태
        Assertions.assertEquals(a + b, sum);

        bw.write("1 + 2 = 4 를 JUnit으로 테스트 \n");
    }

    // 전체 테스트 시작 전 1회 실행하는 어노테이션으로 static 선언이 필요
    @BeforeAll
    static void beforeAll() throws IOException {
        bw.write("@BeforeAll 어노테이션 실행 \n");
//        bw.flush();
    }

    // 전체 테스트 종료 전 1회 실행하는 어노테이션으로 static 선언이 필요
    @AfterAll
    static void afterAll() throws IOException {
        bw.write("@AfterAll 어노테이션 실행 \n");

        bw.flush();
        bw.close();
        br.close();
    }

    // 각 테스트 메서드 시작 전 마다 실행
    @BeforeEach
    public void beforeEach() throws IOException {
        bw.write("@BeforeEach 어노테이션 실행 \n");
    }

    // 각 테스트 메서드 종료 전 마다 실행
    @AfterEach
    public void afterEach() throws IOException {
        bw.write("@AfterEach 어노테이션 실행 \n");

        bw.flush();
    }
}
