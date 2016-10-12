package com.sky.vr.data.source;

import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResourceModel;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sky on 16-10-12.
 */

public class VideoDataRepository implements VideoDataSource {

    private VideoSourceFactory mVideoSourceFactory;

    private VideoDataSource mLocal;
    private VideoDataSource mRemote;

    public VideoDataRepository(VideoSourceFactory videoSourceFactory) {
        mVideoSourceFactory = videoSourceFactory;

        mLocal = mVideoSourceFactory.createLocalSource();
        mRemote = mVideoSourceFactory.createRemoteSource();
    }

    @Override
    public Observable<CategoryModel> getCategory() {

        Observable<CategoryModel> localObservable = mLocal.getCategory();
        Observable<CategoryModel> remoteObservable = mRemote
                .getCategory()
                .doOnNext(new Action1<CategoryModel>() {
                    @Override
                    public void call(CategoryModel model) {
                        // 保存缓存到本地
                        saveCategory(model);
                    }
                });

        return Observable
                .concat(localObservable, remoteObservable)
                .takeFirst(new Func1<CategoryModel, Boolean>() {
                    @Override
                    public Boolean call(CategoryModel model) {
                        return model != null;
                    }
                });
    }

    @Override
    public void saveCategory(CategoryModel model) {
        // 保存数据
        mLocal.saveCategory(model);
        mRemote.saveCategory(model);
    }

    @Override
    public Observable<ResourceModel> getTagsResource(int resId, int tag, int start, int num) {

        Observable<ResourceModel> localObservable = mLocal.getTagsResource(resId, tag, start, num);
        Observable<ResourceModel> remoteObservable = mRemote.getTagsResource(resId, tag, start, num);

        return Observable
                .concat(localObservable, remoteObservable)
                .takeFirst(new Func1<ResourceModel, Boolean>() {
                    @Override
                    public Boolean call(ResourceModel model) {
                        return model != null;
                    }
                });
    }
}
