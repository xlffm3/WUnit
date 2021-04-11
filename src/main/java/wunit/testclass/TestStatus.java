package wunit.testclass;

public enum TestStatus {
    PASSED("Passed"),
    FAILED("Failed"),
    IGNORED("Ignored");

    private static final String MESSAGE_FORMAT = "[Test %s] :: %s - %s";

    private final String status;

    TestStatus(String status) {
        this.status = status;
    }

    public String generateLogMessage(String className, String methodName) {
        return String.format(MESSAGE_FORMAT, status, className, methodName);
    }
}
