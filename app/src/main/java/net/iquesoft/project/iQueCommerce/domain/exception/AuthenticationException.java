package net.iquesoft.project.iQueCommerce.domain.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String detailMessage) {
        super(detailMessage);
    }

    public AuthenticationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
