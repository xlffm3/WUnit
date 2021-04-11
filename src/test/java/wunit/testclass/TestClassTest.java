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

    @DisplayName("클래스의 Test 애너테이션이 붙은 메서드들을 검증한다.")
    @Test
    void runTest() throws ClassNotFoundException {
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        Logger logger = (Logger) LoggerFactory.getLogger(TestClass.class);
        logger.addAppender(listAppender);

        listAppender.start();
        Class<?> testClassReflection = Class.forName("test.domain.PersonTest");
        TestClass testClass = new TestClass(testClassReflection);
        testClass.runTests();

        List<ILoggingEvent> list = listAppender.list;
        String message = list.get(0).getMessage();
        Level level = list.get(0).getLevel();

        assertThat(message).isEqualTo("[Test Passed] :: PersonTest - move");
        assertThat(level).isEqualTo(Level.INFO);
    }
}
