package calculator;

import static calculator.DelimiterHeader.PREFIX;
import static calculator.DelimiterHeader.SUFFIX;

public class InputParser {
    public static String extractPayload(String input) {
        int suffixIndex = input.indexOf(SUFFIX.getValue(), PREFIX.getValue().length());
        if (suffixIndex == -1) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }
        return input.substring(suffixIndex + SUFFIX.getValue().length());
    }
}
