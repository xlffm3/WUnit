package wunit.testclass;

import java.util.List;
import java.util.stream.Collectors;

public class TestClasses {

    private final List<TestClass> testClasses;

    private TestClasses(List<TestClass> testClasses) {
        this.testClasses = testClasses;
    }

    public static TestClasses from(List<Class<?>> testClasses) {
        List<TestClass> mappedTestClasses = testClasses.stream()
                .map(TestClass::new)
                .collect(Collectors.toList());
        return new TestClasses(mappedTestClasses);
    }

    public void runTests() {
        testClasses.forEach(TestClass::runTests);
    }
}
