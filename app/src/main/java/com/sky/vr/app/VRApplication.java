package com.sky.vr.app;

import android.app.Application;

import com.sky.vr.data.cache.CacheManager;
import com.sky.vr.data.cache.impl.CacheManagerImpl;

/**
 * Created by sky on 16-9-26.
 */

public class VRApplication extends Application {

    private static CacheManager mCacheManager;

    public static CacheManager getCacheManager() {
        return mCacheManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mCacheManager == null) {
            // 创建缓存
            mCacheManager = new CacheManagerImpl(getApplicationContext());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        if (mCacheManager != null) {
            // 关闭缓存
            mCacheManager.close();
            mCacheManager = null;
        }
    }
}
