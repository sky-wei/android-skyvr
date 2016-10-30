package com.sky.vr.util;

import com.sky.android.common.utils.Alog;

import java.io.File;

/**
 * Created by sky on 16-10-30.
 */

public class FileUtil {

    public static final String TAG = FileUtil.class.getSimpleName();

    public static void delete(File file) {

        if (file == null || !file.exists()) return ;

        try {
            // 删除文件
            file.delete();
        } catch (Exception e) {
            Alog.e(TAG, "删除文件失败");
        }
    }
}
