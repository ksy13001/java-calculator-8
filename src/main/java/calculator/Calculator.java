package calculator;

import java.util.List;


public class Calculator {

    private Calculator(){
    }

    public static int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            try {
                sum = Math.addExact(sum, number);
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("합산 결과가 int 범위를 초과했습니다.");
            }
        }
        return sum;
    }
}
