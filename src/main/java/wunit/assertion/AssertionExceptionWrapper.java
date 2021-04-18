package wunit.assertion;

import java.util.Objects;

public class AssertionExceptionWrapper {
    private static final String NONE_EXCEPTION_NAME = "None";

    private final RuntimeException actualException;

    public AssertionExceptionWrapper(RuntimeException actualException) {
        this.actualException = actualException;
    }

    public AssertionExceptionWrapper() {
        this(null);
    }

    public AssertionExceptionWrapper isInstanceOf(Class<? extends Throwable> expectedExceptionClass) {
        if (!isExceptionThrown()) {
            throw new AssertionFailureException(expectedExceptionClass.getSimpleName(), NONE_EXCEPTION_NAME);
        }
        Class<? extends RuntimeException> actualExceptionClass = actualException.getClass();
        if (actualExceptionClass != expectedExceptionClass) {
            throw new AssertionFailureException(expectedExceptionClass.getSimpleName(), actualExceptionClass.getSimpleName());
        }
        return this;
    }

    private boolean isExceptionThrown() {
        return !Objects.isNull(actualException);
    }

    public AssertionExceptionWrapper hasMessage(String expectedMessage) {
        if (!isExceptionThrown()) {
            throw new AssertionFailureException(expectedMessage, NONE_EXCEPTION_NAME);
        }
        String actualMessage = actualException.getMessage();
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionFailureException(expectedMessage, actualMessage);
        }
        return this;
    }

    public void doesNotThrowAnyException() {
        if (isExceptionThrown()) {
            throw new AssertionFailureException(NONE_EXCEPTION_NAME, actualException.getClass().getSimpleName());
        }
    }
}
