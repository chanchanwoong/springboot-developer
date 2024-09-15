import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {
    @Test
    public void junitQuiz3() {
        System.out.println("첫 테스트");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("두 번째 테스트");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Hello");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Bye");
    }
}
