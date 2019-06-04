package com.wkz.hover;

import android.content.Context;
import android.content.res.Resources;

public class StatusBarTools {

    /**
     * Return the height of the status bar
     *
     * @param context The context
     * @return Height of the status bar
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
