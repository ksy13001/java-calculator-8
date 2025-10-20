package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    private static final String PROMPT = "덧셈할 문자열을 입력해 주세요.";
    private Input(){}

    static String readInput(){
        System.out.println(PROMPT);
        return validateInput(Console.readLine());
    }

    private static String validateInput(String input){
        if(input == null){
            throw new IllegalStateException("입력이 없습니다.");
        }
        return input;
    }
}
