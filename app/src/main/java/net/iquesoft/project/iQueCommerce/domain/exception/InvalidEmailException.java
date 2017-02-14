package net.iquesoft.project.iQueCommerce.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super();
    }

    public InvalidEmailException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidEmailException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidEmailException(Throwable throwable) {
        super(throwable);
    }
}
