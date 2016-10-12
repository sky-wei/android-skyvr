package com.sky.vr.data.source;

import android.content.Context;

import com.sky.vr.data.source.local.VideoLocalDataSource;
import com.sky.vr.data.source.remote.VideoRemoteDataSource;

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
        return new VideoLocalDataSource(mContext);
    }

    public VideoDataSource createRemoteSource() {
        return new VideoRemoteDataSource();
    }
}
