package com.sky.vr.data.source;

import android.content.Context;

import com.sky.vr.data.source.disk.DiskVideoDataSource;
import com.sky.vr.data.source.cloud.CloudVideoDataSource;

/**
 * Created by sky on 16-10-12.
 */

public class VideoSourceFactory {

    private Context mContext;

    public VideoSourceFactory(Context context) {
        mContext = context;
    }

    public VideoDataSource create() {
        return createRemoteSource();
    }

    public VideoDataSource createLocalSource() {
        return new DiskVideoDataSource(mContext);
    }

    public VideoDataSource createRemoteSource() {
        return new CloudVideoDataSource();
    }
}
