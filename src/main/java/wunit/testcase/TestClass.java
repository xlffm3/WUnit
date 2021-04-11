package wunit.testcase;

public class TestClass {

    private final TestCases testCases;

    private TestClass(TestCases testCases) {
        this.testCases = testCases;
    }

    public static TestClass from(Class<?> testClass) {
        TestCases testCases = TestCases.from(testClass);
        return new TestClass(testCases);
    }

    public void runTests() {
        testCases.runTests();
    }
}
