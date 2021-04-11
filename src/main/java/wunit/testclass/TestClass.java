package wunit.testclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wunit.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);

    private final Class<?> testClass;

    public TestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public void runTests() {
        Arrays.stream(testClass.getDeclaredMethods())
                .forEach(this::runEachTest);
    }

    private void runEachTest(Method method) {
        if (!method.isAnnotationPresent(Test.class)) {
            return;
        }
        try {
            method.invoke(null);
            LOGGER.info(TestStatus.PASSED.generateLogMessage(testClass.getSimpleName(), method.getName()));
        } catch (InvocationTargetException invocationTargetException) {
            LOGGER.info(TestStatus.FAILED.generateLogMessage(testClass.getSimpleName(), method.getName()));
            LOGGER.info(invocationTargetException.getTargetException().getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            LOGGER.info(TestStatus.IGNORED.generateLogMessage(testClass.getSimpleName(), method.getName()));
        }
    }
}
