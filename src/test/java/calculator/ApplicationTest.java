package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 기본_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("//?\\n1,2:3?4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_구분자가_공백인_경우() {
        assertSimpleTest(() -> {
            run("// \\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_구분자_마이너스인_경우() {
        assertSimpleTest(() -> {
            run("//-\\n1-2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 두자릿수_이상_숫자인_경우() {
        assertSimpleTest(() -> {
            run("1,10,100,1000");
            assertThat(output()).contains("결과 : 1111");
        });
    }

    @Test
    void 빈_문자열(){
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 구분자가_연속_입력(){
        assertSimpleTest(() -> {
            run("1,,,2,,,3");
            assertThat(output()).contains("결과 : 6");
        });
    }


    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 문자열에_공백_포함된_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() ->
                        runException("1, 2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 선언되지않은_구분자_사용한경우_예외(){
        assertSimpleTest(() ->
                assertThatThrownBy(() ->
                        runException("1,2?3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀구분자가_숫자일경우_예외(){
        assertSimpleTest(() ->
                assertThatThrownBy(() ->
                        runException("//1\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }


    @Test
    void 합산_오버플로우_예외(){
        assertSimpleTest(() ->
            assertThatThrownBy(() ->
                    runException("9223372036854775807"+",1"))
                    .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 추출_오버플로우_예외(){
        assertSimpleTest(() ->
                assertThatThrownBy(() ->
                        runException("9223372036854775808"+",1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
