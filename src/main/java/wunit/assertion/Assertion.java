package wunit.assertion;

public class Assertion {

    private Assertion() {
    }

    public static void assertTrue(boolean isTrue) {
        if (!isTrue) {
            throw new AssertionFailureException("True", "False");
        }
    }

    public static AssertionExceptionWrapper assertThatCode(Runnable runnable) {
        try {
            runnable.run();
            return new AssertionExceptionWrapper();
        } catch (RuntimeException runtimeException) {
            return new AssertionExceptionWrapper(runtimeException);
        }
    }
}
