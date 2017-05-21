package com.sky.vr.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sky.android.common.utils.Alog;
import com.sky.vr.activity.CommonActivity;
import com.sky.vr.activity.RetailsActivity;

/**
 * Created by sky on 17-5-21.
 */

public final class ActivityManager {

    private static final String TAG = "ActivityManager";

    public static boolean startCommonActivity(Context context, int title, String fName) {
        return startCommonActivity(context, title, fName, true);
    }

    public static boolean startCommonActivity(Context context, int title, String fName, boolean supportFragment) {

        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra(CommonActivity.TITLE, title);
        intent.putExtra(CommonActivity.F_NAME, fName);
        intent.putExtra(CommonActivity.SUPPORT_FRAGMENT, supportFragment);

        return startActivity(context, intent);
    }

    public static boolean startRetailsActivity(Context context, String title, String fName) {
        return startRetailsActivity(context, title, fName, null);
    }

    public static boolean startRetailsActivity(Context context, String title, String fName, Bundle args) {

        Intent intent = new Intent(context, RetailsActivity.class);
        intent.putExtra(RetailsActivity.TITLE, title);
        intent.putExtra(RetailsActivity.F_NAME, fName);
        intent.putExtra(RetailsActivity.ARGS, args);

        return startActivity(context, intent);
    }

    public static boolean startActivity(Context context, Class tClass) {

        if (context == null || tClass == null) return false;

        return startActivity(context, new Intent(context, tClass));
    }

    public static boolean startActivity(Context context, Intent intent) {

        if (context == null || intent == null) return false;

        try {
            // 获取目标包名
            String packageName = intent.getPackage();

            // 设置启动参数
            if (!TextUtils.isEmpty(packageName)
                    && !TextUtils.equals(packageName, context.getPackageName())) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            // 启动Activity
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Alog.e(TAG, "启动Activity异常", e);
        }
        return false;
    }
}
