package com.sky.vr.data.source;

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
    public void getVideCategory(LoadCategoryCallback callback) {

        // 请求数据
        mRemote.getVideCategory(callback);
    }
}
