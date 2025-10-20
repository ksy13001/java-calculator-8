package calculator;
import java.util.List;

import static calculator.DelimiterHeader.PREFIX;
import static calculator.DelimiterHeader.SUFFIX;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Input.readInput();
        List<Integer> numbers = DelimiterParser.parseDelimiter(input)
                .map(s -> {
                    NumberExtractor numberExtractor = new NumberExtractor(s);
                    return numberExtractor.extractNumbers(InputParser.extractPayload(input));
                })
                .orElseGet(()->{
                    NumberExtractor numberExtractor = new NumberExtractor();
                    return numberExtractor.extractNumbers(input);
                });
        Output.printResult(Calculator.sum(numbers));
    }


}
