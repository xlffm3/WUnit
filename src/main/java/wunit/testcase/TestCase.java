package wunit.testcase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCase.class);
    private static final String LOG_FORMAT = "[Test {}] :: {} - {}";
    private static final String EXCEPTION_MESSAGE_FORMAT = "    [Message] :: {}";

    private final String className;
    private final Method method;

    public TestCase(String className, Method method) {
        this.className = className;
        this.method = method;
    }

    public void runTest() {
        try {
            method.invoke(null);
            LOGGER.info(LOG_FORMAT, "Passed", className, method.getName());
        } catch (InvocationTargetException invocationTargetException) {
            LOGGER.info(LOG_FORMAT, "Failed", className, method.getName());
            LOGGER.info(EXCEPTION_MESSAGE_FORMAT, invocationTargetException.getTargetException().getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            LOGGER.info(LOG_FORMAT, "Ignored", className, method.getName());
        }
    }
}
