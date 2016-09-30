package com.sky.vr.data.source;

import com.sky.vr.data.mojing.Tags;

import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRepository implements VideoDataSource {

    private VideoDataSource mLocal;
    private VideoDataSource mRemote;

    public VideoRepository(VideoDataSource local, VideoDataSource remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public Observable<Tags> getVideCategory() {

        // 请求数据
        return mRemote.getVideCategory();
    }
}
