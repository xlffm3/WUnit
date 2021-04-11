package wunit.testclass;

public enum TestCaseResult {
    PASS,
    FAIL,
    ERROR;

    public boolean isSameAs(TestCaseResult expectedResult) {
        return this == expectedResult;
    }
}
