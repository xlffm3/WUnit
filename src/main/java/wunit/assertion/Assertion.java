package wunit.assertion;

public class Assertion {

    private Assertion() {
    }

    public static void assertTrue(boolean isTrue) {
        if (!isTrue) {
            throw new AssertionFailureException();
        }
    }

    public static AssertionExceptionWrapper assertThatCode(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException runtimeException) {
            return new AssertionExceptionWrapper(runtimeException);
        }
        return new AssertionExceptionWrapper(new NoneException());
    }
}
