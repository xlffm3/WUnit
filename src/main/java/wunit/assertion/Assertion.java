package wunit.assertion;

public class Assertion {

    public interface Runnable {
        void run() throws Throwable;
    }

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
        } catch (Throwable throwable) {
            return new AssertionExceptionWrapper(throwable);
        }
    }
}
