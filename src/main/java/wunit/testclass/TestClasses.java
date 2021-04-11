package wunit.testclass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestClasses {

    private final List<TestClass> testClasses;

    private TestClasses(List<TestClass> testClasses) {
        this.testClasses = testClasses;
    }

    public static TestClasses from(List<Class<?>> testClasses) {
        List<TestClass> mappedTestClasses = testClasses.stream()
                .map(TestClass::from)
                .collect(Collectors.toList());
        return new TestClasses(mappedTestClasses);
    }

    public VerificationResult runAllTests() {
        List<TestCaseResult> testCaseResults = testClasses.stream()
                .map(TestClass::runTests)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return new VerificationResult(testCaseResults);
    }
}
