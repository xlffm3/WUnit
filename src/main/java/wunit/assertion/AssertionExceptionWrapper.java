package wunit.assertion;

public class AssertionExceptionWrapper {

    private final RuntimeException runtimeException;

    public AssertionExceptionWrapper(RuntimeException runtimeException) {
        this.runtimeException = runtimeException;
    }

    public AssertionExceptionWrapper isInstanceOf(Class<? extends RuntimeException> exceptionClass) {
        if (runtimeException.getClass() == exceptionClass) {
            return this;
        }
        throw new AssertionFailureException(exceptionClass.getSimpleName(), runtimeException.getClass().getSimpleName());
    }

    public AssertionExceptionWrapper hasMessage(String message) {
        if (runtimeException.getMessage().equals(message)) {
            return this;
        }
        throw new AssertionFailureException(message, runtimeException.getMessage());
    }

    public void doesNotThrowAnyException() {
        if (runtimeException.getClass() != NoneException.class) {
            throw new AssertionFailureException("None", runtimeException.getClass().getSimpleName());
        }
    }
}
