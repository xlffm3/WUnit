package wunit.testcase;

import wunit.annotation.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestCases {

    private final List<TestCase> testCases;

    private TestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public static TestCases from(Class<?> testClass) {
        List<TestCase> testCases = Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .map(method -> new TestCase(testClass.getSimpleName(), method))
                .collect(Collectors.toList());
        return new TestCases(testCases);
    }

    public void runTests() {
        testCases.forEach(TestCase::runTest);
    }
}
