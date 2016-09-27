package com.sky.vr.app;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by sky on 16-9-26.
 */

public class VRApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(getBaseContext());
    }

    private void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(5)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(10 * 1024 * 1024)
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }
}
