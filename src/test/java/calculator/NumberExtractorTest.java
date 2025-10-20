package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberExtractorTest {

    private NumberExtractor extractor;
    private NumberExtractor customExtractor;
    private final static String CUSTOM_DELIMITER = "?";

    @BeforeEach
    void setup() {
        extractor = new NumberExtractor();
        customExtractor = new NumberExtractor(CUSTOM_DELIMITER);
    }

    @Test
    void 기본구분자로_숫자추출() {
        String input = "1,2:3";
        List<Integer> numbers = extractor.extractNumbers(input);
        assertThat(numbers).containsExactly(1, 2, 3);
    }

    @Test
    void 커스텀구분자_있는경우_숫자추출() {
        String input = "1,2:3?4";
        List<Integer> numbers = customExtractor.extractNumbers(input);
        assertThat(numbers).containsExactly(1, 2, 3, 4);
    }

    @Test
    void 입력없는경우_empty_리스트_반환() {
        String input = "";
        List<Integer> numbers = customExtractor.extractNumbers(input);
        assertThat(numbers).isEmpty();
    }

    @Test
    void 공백_있는경우_숫자만_추출() {
        String input = " 1 ,  2  :   3 ,";
        List<Integer> numbers = extractor.extractNumbers(input);
        assertThat(numbers).containsExactly(1, 2, 3);
    }

    @Test
    void 구분자_연속입력도_허용() {
        String input = "1,,,:2::3";
        List<Integer> numbers = extractor.extractNumbers(input);
        assertThat(numbers).containsExactly(1, 2, 3);
    }

    @Test
    void 숫자대신_구분자가아닌_문자가_추출된경우_예외() {
        String input = "1,2,a";

        assertThatThrownBy(() -> extractor.extractNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음수_입력시_예외() {
        String input = "1,2,-1";
        assertThatThrownBy(() -> extractor.extractNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
