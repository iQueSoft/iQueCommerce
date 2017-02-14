package net.iquesoft.project.iQueCommerce.domain;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class AlertDialogMaker {
    private final Context context;
    private final String title;
    private final String message;
    private final String positiveButtonText;
    private final String negativeButtonText;
    private final int iconID;

    public AlertDialogMaker(Context context, String title, String body, String positiveButtonText, String negativeButtonString, int iconID) {
        this.context = context;
        this.title = title;
        this.message = body;
        this.positiveButtonText = positiveButtonText;
        this.negativeButtonText = negativeButtonString;
        this.iconID = iconID;
    }

    public AlertDialog getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setIcon(iconID)
                .setPositiveButton(positiveButtonText, (dialog, which) -> {

                })
                .setNegativeButton(negativeButtonText, (dialog, which) -> {

                });
        return builder.create();
    }
}
