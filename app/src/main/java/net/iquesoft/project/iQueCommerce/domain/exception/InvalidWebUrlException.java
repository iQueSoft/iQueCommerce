package net.iquesoft.project.iQueCommerce.domain.exception;

public class InvalidWebUrlException extends RuntimeException {
    public InvalidWebUrlException() {
        super();
    }

    public InvalidWebUrlException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidWebUrlException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidWebUrlException(Throwable throwable) {
        super(throwable);
    }
}
