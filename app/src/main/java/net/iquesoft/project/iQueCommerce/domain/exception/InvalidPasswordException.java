package net.iquesoft.project.iQueCommerce.domain.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidPasswordException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidPasswordException(Throwable throwable) {
        super(throwable);
    }
}
