package net.iquesoft.project.iQueCommerce.domain.exception;

import android.content.Context;

import net.iquesoft.project.iQueCommerce.R;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context   Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, RuntimeException exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof InvalidEmailException) {
            message = context.getString(R.string.exception_message_invalid_email);
        } else if (exception instanceof InvalidNameException) {
            message = context.getString(R.string.exception_message_invalid_name);
        } else if (exception instanceof InvalidIpAddressException) {
            message = context.getString(R.string.exception_message_invalid_ip_address);
        } else if (exception instanceof InvalidPasswordException) {
            message = context.getString(R.string.exception_message_invalid_password);
        } else if (exception instanceof InvalidWebUrlException) {
            message = context.getString(R.string.exception_message_invalid_web_url);
        } else if (exception instanceof EmptyFieldException) {
            message = context.getString(R.string.exception_message_empty_field);
        }

        return message;
    }
}
