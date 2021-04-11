package wunit.testclass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class TestClassTest {

    @DisplayName("클래스의 WTest 애너테이션이 붙은 메서드들을 검증한다.")
    @Test
    void runTest() throws ClassNotFoundException {
        Class<?> testClassReflection = Class.forName("test.domain.PersonTest");
        TestClass testClass = new TestClass(testClassReflection);

        assertThatCode(testClass::runTest).doesNotThrowAnyException();
    }
}
