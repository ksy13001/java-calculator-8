package calculator;

import java.util.Optional;

import static calculator.DelimiterHeader.PREFIX;
import static calculator.DelimiterHeader.SUFFIX;

public class DelimiterParser {


    private DelimiterParser() {
    }

    static Optional<String> parseDelimiter(String input) {
        if (!hasPrefix(input)) {
            return Optional.empty();
        }

        int suffixIndex = findSuffixIndex(input);
        return extractCustomDelimiter(input, suffixIndex)
                .map(DelimiterParser::validateCustomDelimiter);
    }

    private static boolean hasPrefix(String input) {
        return input.startsWith(PREFIX.getValue());
    }

    private static int findSuffixIndex(String input) {
        int suffixIndex = input.indexOf(SUFFIX.getValue(), PREFIX.getValue().length());
        if (suffixIndex == -1) {
            throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
        }
        return suffixIndex;
    }

    private static Optional<String> extractCustomDelimiter(String input, int suffixIndex) {
        String customDelimiter = input.substring(PREFIX.getValue().length(), suffixIndex);
        if (customDelimiter.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(customDelimiter);
    }

    private static String validateCustomDelimiter(String customDelimiter) {
        requireSingleCharacter(customDelimiter);
        forbidDigitDelimiter(customDelimiter);
        return customDelimiter;
    }

    private static void requireSingleCharacter(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자의 길이는 1글자여야 합니다.");
        }
    }

    private static void forbidDigitDelimiter(String customDelimiter) {
        if (Character.isDigit(customDelimiter.charAt(0))) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자를 사용할 수 없습니다");
        }
    }
}