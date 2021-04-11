package wunit.testclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wunit.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);
    private static final String LOG_FORMAT = "[Test {}] :: {} - {}";
    private static final String EXCEPTION_MESSAGE_FORMAT = "    [Message] :: {}";

    private final Class<?> testClass;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public void runTests() {
        Arrays.stream(testClass.getDeclaredMethods())
                .forEach(this::runEachTest);
    }

    private void runEachTest(Method method) {
        if (method.isAnnotationPresent(Test.class)) {
            logResult(method);
        }
    }

    private void logResult(Method method) {
        try {
            method.invoke(null);
            LOGGER.info(LOG_FORMAT, "Passed", testClass.getSimpleName(), method.getName());
        } catch (InvocationTargetException invocationTargetException) {
            LOGGER.info(LOG_FORMAT, "Failed", testClass.getSimpleName(), method.getName());
            LOGGER.info(EXCEPTION_MESSAGE_FORMAT, invocationTargetException.getTargetException().getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            LOGGER.info(LOG_FORMAT, "Ignored", testClass.getSimpleName(), method.getName());
        }
    }
}
