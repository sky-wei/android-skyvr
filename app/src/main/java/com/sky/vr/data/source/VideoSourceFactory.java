package com.sky.vr.data.source;

import android.content.Context;

import com.sky.vr.app.VRApplication;
import com.sky.vr.data.cache.VideoCache;
import com.sky.vr.data.cache.impl.VideoCacheImpl;
import com.sky.vr.data.source.disk.DiskVideoDataSource;
import com.sky.vr.data.source.cloud.CloudVideoDataSource;

/**
 * Created by sky on 16-10-12.
 */

public class VideoSourceFactory {

    private Context mContext;
    private VideoCache mCache;

    public VideoSourceFactory(Context context) {
        mContext = context;
        mCache = new VideoCacheImpl(VRApplication.getCacheManager());
    }

    public VideoDataSource create() {
        return createRemoteSource();
    }

    public VideoDataSource createLocalSource() {
        return new DiskVideoDataSource(mContext, mCache);
    }

    public VideoDataSource createRemoteSource() {
        return new CloudVideoDataSource(mCache);
    }
}
