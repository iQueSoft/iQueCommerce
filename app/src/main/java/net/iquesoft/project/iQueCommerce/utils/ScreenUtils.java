package net.iquesoft.project.iQueCommerce.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
    final Context context;
    private final DisplaySize displaySize;

    public ScreenUtils(Context context) {
        this.context = context;
        displaySize = new DisplaySize().invoke();
    }


    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public int getDisplayWidthInPx() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public int getDisplayHeightInPx() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getGridViewWidth() {
        return displaySize.getDisplayWidth();
    }

    public int getGridViewHeight() {
        return displaySize.getDisplayHeight();
    }

    private class DisplaySize {
        private int lWidth;
        private int lHeight;

        public int getDisplayWidth() {
            return lWidth;
        }

        public int getDisplayHeight() {
            return lHeight;
        }

        public DisplaySize invoke() {
            DisplayMetrics displaySize = context.getResources().getDisplayMetrics();
            float dpWidth = displaySize.widthPixels / displaySize.density;
            lWidth = (int) (dpWidth / 2 - dpWidth / 5);
            lHeight = (int) ((double) lWidth * 1.5);
            lWidth = displaySize.widthPixels / 2 - 32;
            lHeight = (int) ((double) lWidth * 1.3);
            return this;
        }
    }
}
