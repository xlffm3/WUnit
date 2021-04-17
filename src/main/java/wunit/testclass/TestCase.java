package wunit.testclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wunit.assertion.AssertionFailureException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCase.class);
    private static final String LOG_FORMAT = "[Test {}] :: {} - {}";
    private static final String EXCEPTION_MESSAGE_FORMAT = "    [Message] :: {}";

    private final String className;
    private final Object object;
    private final Method method;

    public TestCase(String className, Object object, Method method) {
        this.className = className;
        this.object = object;
        this.method = method;
    }

    public TestCaseResult runTest() {
        try {
            method.invoke(object);
            LOGGER.info(LOG_FORMAT, "Passed", className, method.getName());
            return TestCaseResult.PASS;
        } catch (InvocationTargetException invocationTargetException) {
            return selectTestCaseResultByCause(invocationTargetException);
        } catch (IllegalAccessException illegalAccessException) {
            LOGGER.info(LOG_FORMAT, " Error", className, method.getName());
            return TestCaseResult.ERROR;
        }
    }

    private TestCaseResult selectTestCaseResultByCause(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        String exceptionMessage = targetException.getMessage();
        if (targetException instanceof AssertionFailureException) {
            logResultWithExceptionMessage("Failed", exceptionMessage);
            return TestCaseResult.FAIL;
        }
        logResultWithExceptionMessage(" Error", exceptionMessage);
        return TestCaseResult.ERROR;
    }

    public void logResultWithExceptionMessage(String resultType, String exceptionMessage) {
        LOGGER.info(LOG_FORMAT, resultType, className, method.getName());
        LOGGER.info(EXCEPTION_MESSAGE_FORMAT, exceptionMessage);
    }
}
