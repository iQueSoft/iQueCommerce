package net.iquesoft.project.iQueCommerce.domain.exception;

import net.iquesoft.project.iQueCommerce.utils.Constants;

public class LoginInternalException extends InterruptedException {

    public LoginInternalException() {

    }

    public String getMessage(int code) {

        String message = null;
        if (code == Constants.ERROR_EMPTY_EMAIL | code == Constants.ERROR_EMPTY_PASSWORD) {
            message = Constants.ERROR_MESSAGE_EMPTY_FIELD;
        } else if (code == Constants.ERROR_INVALID_EMAIL) {
            message = Constants.ERROR_MESSAGE_INVALID_EMAIL;
        } else if (code == Constants.ERROR_INVALID_PASSWORD) {
            message = Constants.ERROR_MESSAGE_INVALID_PASSWORD;
        } else if (code == Constants.ERROR_CONNECTION_LOST) {
            message = Constants.ERROR_MESSAGE_CONNECTION_LOST;
        } else if (code == Constants.ERROR_UNKNOWN) {
            message = Constants.ERROR_MESSAGE_UNKNOWN_ERROR;
        }
        return message;
    }
}
