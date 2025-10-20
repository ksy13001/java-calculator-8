package calculator;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DelimiterParserTest {


    @Test
    void 문자열에_커스텀구분자_존재할경우_반환(){
        String input = "//;\\n1";
        Optional<String> delimiter = DelimiterParser.parseDelimiter(input);
        assertThat(delimiter).hasValue(";");
    }

    @Test
    void 문자열에_커스텀구분자_없는경우_empty(){
        String input = "1,2";
        Optional<String> delimiter = DelimiterParser.parseDelimiter(input);
        assertThat(delimiter).isEmpty();
    }

    @Test
    void 커스텀구분자가_공백인경우(){
        String input = "// \\n";
        Optional<String> delimiter = DelimiterParser.parseDelimiter(input);
        assertThat(delimiter).hasValue(" ");
    }

    @Test
    void 올바른_형식이지만_커스텀구분자가_없는경우_empty(){
        String input = "//\\n";
        Optional<String> delimiter = DelimiterParser.parseDelimiter(input);
        assertThat(delimiter).isEmpty();
    }

    @Test
    void 커스텀구분자_길이가_지정된길이_초과인경우_예외(){
        String input = "//??\\n1,2,3";
        assertThatThrownBy(()->DelimiterParser.parseDelimiter(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀구분자가_숫자일경우_예외(){
        String input = "//9\\n19293";
        assertThatThrownBy(()->DelimiterParser.parseDelimiter(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 접두사는존재하지만_접미사가존재하지않을경우_예외(){
        String input = "//?1,2?3";
        assertThatThrownBy(()->DelimiterParser.parseDelimiter(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
