package com.igor.latte.utils.dimen;

import com.igor.latte.app.Latte;

/**
 * Created by Igor on 2017/9/2.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        return Latte.getApplicationContext()
                .getResources()
                .getDisplayMetrics()
                .widthPixels;
    }

    public static int getScreenHeight() {
        return Latte.getApplicationContext()
                .getResources()
                .getDisplayMetrics()
                .heightPixels;
    }
}
