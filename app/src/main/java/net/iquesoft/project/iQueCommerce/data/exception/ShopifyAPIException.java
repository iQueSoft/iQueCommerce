package net.iquesoft.project.iQueCommerce.data.exception;

import java.util.Map;

public class ShopifyAPIException extends RuntimeException {

    public ShopifyAPIException() {
    }

    public ShopifyAPIException(String message) {
        super(message);
    }

    public ShopifyAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopifyAPIException(Throwable cause) {
        super(cause);
    }


    public ShopifyAPIException(Map<String, String> errors) {
    }
}
