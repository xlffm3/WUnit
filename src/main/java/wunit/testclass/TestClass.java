package wunit.testclass;

import wunit.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestClass {
    private static final Function<Method, Method> ACCESSIBLE_MODIFIER = (method) -> {
        method.setAccessible(true);
        return method;
    };

    private final List<TestCase> testCases;

    private TestClass(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public static TestClass from(Class<?> testClass) {
        List<TestCase> testCases = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(ACCESSIBLE_MODIFIER)
                .map(method -> new TestCase(testClass.getSimpleName(), createObjectByTypeToken(testClass), method))
                .collect(Collectors.toList());
        return new TestClass(testCases);
    }

    private static Object createObjectByTypeToken(Class<?> testClass) {
        try {
            Constructor<?> declaredConstructor = testClass.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot generate test environment.");
        }
    }

    public List<TestCaseResult> runTests() {
        return testCases.stream()
                .map(TestCase::runTest)
                .collect(Collectors.toList());
    }
}
