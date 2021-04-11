package wunit.testclass;

import wunit.annotation.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {

    private final List<TestCase> testCases;

    private TestClass(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public static TestClass from(Class<?> testClass) {
        List<TestCase> testCases = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(method -> new TestCase(testClass.getSimpleName(), method))
                .collect(Collectors.toList());
        return new TestClass(testCases);
    }

    public List<TestCaseResult> runTests() {
        return testCases.stream()
                .map(TestCase::runTest)
                .collect(Collectors.toList());
    }
}
