package wunit.assertion;

import java.util.Optional;

public class AssertionExceptionWrapper {

    private final RuntimeException runtimeException;

    public AssertionExceptionWrapper(RuntimeException runtimeException) {
        this.runtimeException = runtimeException;
    }

    public AssertionExceptionWrapper() {
        this(null);
    }

    public AssertionExceptionWrapper isInstanceOf(Class<? extends Throwable> exceptionClass) {
        if (runtimeException != null && runtimeException.getClass() == exceptionClass) {
            return this;
        }
        throw new AssertionFailureException(exceptionClass.getSimpleName(), runtimeException.getClass().getSimpleName());
    }

    public AssertionExceptionWrapper hasMessage(String message) {
        if (runtimeException != null && runtimeException.getMessage().equals(message)) {
            return this;
        }
        throw new AssertionFailureException(message, Optional.ofNullable(runtimeException).orElseGet(RuntimeException::new).getMessage());
    }

    public void doesNotThrowAnyException() {
        if (runtimeException != null) {
            throw new AssertionFailureException("None", runtimeException.getClass().getSimpleName());
        }
    }
}
