import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {
    /**
     * 기존에는 Assertions.assertEquals(변수1, 변수2) 했으나 해당 방식은 가독성이 떨어진다.
     * assertThat(변수1).isEqualTo(변수2) 과 같은 형태는 어떤 테스트를 진행하는지 명확하다.
     *
     * assertThat(변수1).isZero(), isPositive(), isNegative(),,, 가독성이 뛰어나다.
     */

    @Test
    @DisplayName("assertThat 기본 테스트")
    public void junitTest() {
        // given
        String name1 = "박찬웅";
        String name2 = "박찬웅";
        String name3 = "박찬";

        // then
        // 1. 모든 변수가 null이 아닌지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        // 2. name1과 name2가 같은지 확인
        assertThat(name1).isEqualTo(name2);

        // 3. name1과 name3가 같은지 확인
        assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    public void junitTest2() {
        // given
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        // then
        // 1. number1 양수 확인
        assertThat(number1).isPositive();

        // 2. number2 0인지 확인
        assertThat(number2).isZero();

        // 3. number3 음수 확인
        assertThat(number3).isNegative();

        // 4. number1 > number2 확인
        assertThat(number1).isGreaterThan(number2);

        // 5. number3 < number2 확인
        assertThat(number3).isLessThan(number2);
    }
}
