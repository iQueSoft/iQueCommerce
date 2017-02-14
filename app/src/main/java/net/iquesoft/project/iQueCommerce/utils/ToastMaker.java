package net.iquesoft.project.iQueCommerce.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastMaker {

    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showMessage(Context context, String message, duration duration) {
        if (duration.equals(ToastMaker.duration.SHORT)) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public enum duration {
        SHORT,
        LONG
    }
}
