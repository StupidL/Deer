package me.stupidme.deer.util;

import android.content.Context;

/**
 * Created by allen on 17-8-12.
 */

public class UnitUtil {

    public static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }
}
