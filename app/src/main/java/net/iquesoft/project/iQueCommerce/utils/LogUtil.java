package net.iquesoft.project.iQueCommerce.utils;

import android.util.Log;

public class LogUtil {

    public static void makeLog(Object logMessage) {
        Log.d("Seed LOG", "=================================================================\n"
                + logMessage + "\n"
                + "=================================================================");
    }

    public static void makeLog(String tag, String logMessage) {
        Log.d(tag, "=================================================================\n"
                + logMessage + "\n"
                + "=================================================================");
    }

    public static void makeLog(Object o, String logMessage) {
        Log.d(o.getClass().getName(), "=================================================================\n"
                + logMessage + "\n"
                + "=================================================================");
    }


}
