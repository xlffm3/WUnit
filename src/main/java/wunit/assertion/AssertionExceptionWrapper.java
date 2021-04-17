package wunit.assertion;

import java.util.Optional;

public class AssertionExceptionWrapper {

    private final Optional<Throwable> throwable;

    public AssertionExceptionWrapper(Throwable throwable) {
        this.throwable = Optional.ofNullable(throwable);
    }

    public AssertionExceptionWrapper() {
        this(null);
    }

    public AssertionExceptionWrapper isInstanceOf(Class<? extends Throwable> exceptionClass) {
        if (throwable.isPresent() && throwable.get().getClass() == exceptionClass) {
            return this;
        }
        throw new AssertionFailureException(exceptionClass.getSimpleName(), throwable.getClass().getSimpleName());
    }

    public AssertionExceptionWrapper hasMessage(String message) {
        if (throwable.isPresent() && throwable.get().getMessage().equals(message)) {
            return this;
        }
        throw new AssertionFailureException(message, throwable.orElseGet(Throwable::new).getMessage());
    }

    public void doesNotThrowAnyException() {
        if (throwable.isPresent()) {
            throw new AssertionFailureException("None", throwable.getClass().getSimpleName());
        }
    }
}
