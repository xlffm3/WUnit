package wunit.testclass;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestClassTest {

    @DisplayName("NumberTest 클래스에서 Test 애너테이션이 붙은 메서드들을 검증한다.")
    @Test
    void runTests() throws ClassNotFoundException {
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        Logger logger = (Logger) LoggerFactory.getLogger(TestCase.class);
        logger.addAppender(listAppender);
        listAppender.start();

        TestClass testClass = TestClass.from(Class.forName("test.domain.NumberTest"));
        List<TestCaseResult> testCaseResults = testClass.runTests();
        List<ILoggingEvent> testLogs = listAppender.list;
        String message = testLogs.get(0).getFormattedMessage();
        Level level = testLogs.get(0).getLevel();

        assertThat(message).isEqualTo("[Test Failed] :: NumberTest - compareFailed");
        assertThat(level).isEqualTo(Level.INFO);
        assertThat(testCaseResults)
                .containsExactly(TestCaseResult.FAIL, TestCaseResult.PASS, TestCaseResult.ERROR, TestCaseResult.PASS, TestCaseResult.ERROR);
    }
}
