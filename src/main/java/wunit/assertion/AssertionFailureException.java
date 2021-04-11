package wunit.assertion;

public class AssertionFailureException extends RuntimeException {
    private static final String DIFFERENT_EXCEPTION_MESSAGE = "Expected : [%s], Actual : [%s]";

    public AssertionFailureException(String expected, String actual) {
        super(String.format(DIFFERENT_EXCEPTION_MESSAGE, expected, actual));
    }
}
