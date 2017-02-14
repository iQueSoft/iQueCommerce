package net.iquesoft.project.iQueCommerce.data.exception;

public class NoCartException extends RuntimeException {

    public NoCartException() {
        super();
    }

    public NoCartException(String detailMessage) {
        super(detailMessage);
    }

    public NoCartException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NoCartException(Throwable throwable) {
        super(throwable);
    }

}
