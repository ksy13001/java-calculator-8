package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {

    private static final String DEFAULT_DELIMITERS = ",|:";

    private String delimiter;

    public NumberExtractor(){
        this.delimiter = DEFAULT_DELIMITERS;
    }

    public NumberExtractor(String customDelimiter){
        this.delimiter = DEFAULT_DELIMITERS + "|" + Pattern.quote(customDelimiter);
    }

    public List<Integer> extractNumbers(String input){
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::parseIntegerStrict)
                .toList();
    }

    private Integer parseIntegerStrict(String s){
        Integer value = null;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력했습니다.");
        }
        if(value < 0){
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }
        return value;
    }

}
