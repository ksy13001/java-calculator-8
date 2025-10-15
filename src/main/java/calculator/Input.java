package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    private static final String PROMPT = "덧셈할 문자열을 입력해 주세요.";
    private Input(){}

    static String readInput(){
        System.out.println(PROMPT);
        return Console.readLine();
    }
}
