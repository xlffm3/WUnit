package wunit.testclass;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VerificationResultTest {

    @DisplayName("테스트 전체의 통계를 출력한다.")
    @Test
    void logTestStatistics() throws ClassNotFoundException {
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        Logger logger = (Logger) LoggerFactory.getLogger(VerificationResult.class);
        logger.addAppender(listAppender);
        listAppender.start();

        TestClasses testClasses = TestClasses.from(TestClassLoader.loadTestClasses("test"));
        VerificationResult verificationResult = testClasses.runAllTests();
        verificationResult.logTestStatistics();
        List<ILoggingEvent> testLogs = listAppender.list;
        String formattedMessage = testLogs.get(0).getFormattedMessage();

        assertThat(testLogs).hasSize(1);
        assertThat(formattedMessage).isEqualTo("Total : 12, Passed : 5, Failed : 5, Error : 2");
    }
}
