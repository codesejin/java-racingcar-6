package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    @DisplayName("입력받은 문자열을 쉼표(,)기준으로 쪼개기")
    public void carsByStringToArray() {
        // given
        String input = "pobi,woni";
        String[] test = new String[] {"pobi","woni"};
        // when
        String[] carsByStringToArray = InputValidator.carsByStringToArray(input);
        assertArrayEquals(carsByStringToArray,test);
    }

    @Test
    @DisplayName("자동차 이름에 중복이 들어올 경우 IllegalArgumentException 발생")
    public void validateDuplicate() {
        //given
        String[] cars = new String[]{"pobi", "woni", "json", "pobi"};
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->  InputValidator.checkDuplicateCarName(cars))
                .withMessageMatching("자동차 이름은 중복이 될 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름에 중복이 없을 경우 set Collection 반환")
    public void validateNoDuplicate() {
        //given
        String[] cars = new String[]{"pobi", "woni", "json", "sejin"};
        Set<String> expectedCars = new HashSet<>(Arrays.asList(cars));
        // when
        Set<String> checkedCars = InputValidator.checkDuplicateCarName(cars);
        // then
        assertEquals(expectedCars, checkedCars);
    }
}