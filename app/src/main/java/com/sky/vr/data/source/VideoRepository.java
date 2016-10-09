package com.sky.vr.data.source;

import com.sky.vr.data.mojing.Tags;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

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

        // 获取本地
        Observable<Tags> observable = mLocal.getVideCategory();

//        observable.subscribe()

////        observable.
//
//        mRemote.getVideCategory().do

        // 请求数据
        return mRemote.getVideCategory();
    }

    @Override
    public void saveVideCategory(Tags tags) {

        mLocal.saveVideCategory(tags);
        mRemote.saveVideCategory(tags);
    }
}
