package calculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

    @Test
    void 숫자_합산_테스트(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int result = Calculator.sum(list);
        assertThat(result).isEqualTo(55);
    }

    @Test
    void 빈입력_합산_테스트(){
        List<Integer> list = List.of();
        int result = Calculator.sum(list);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void 합산_오버플로우시_예외(){
        List<Integer> numbers = Arrays.asList(Integer.MAX_VALUE, 1);
        assertThatThrownBy(()->Calculator.sum(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}