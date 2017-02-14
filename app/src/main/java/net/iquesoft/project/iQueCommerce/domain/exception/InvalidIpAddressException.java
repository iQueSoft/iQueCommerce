package net.iquesoft.project.iQueCommerce.domain.exception;

public class InvalidIpAddressException extends RuntimeException {
    public InvalidIpAddressException() {
        super();
    }

    public InvalidIpAddressException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidIpAddressException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidIpAddressException(Throwable throwable) {
        super(throwable);
    }
}
