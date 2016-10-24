package com.sky.vr.data.source;

import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.data.model.ResListModel;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sky on 16-10-12.
 */

public class VideoDataRepository implements VideoDataSource {

    private VideoDataSource mLocal;
    private VideoDataSource mRemote;

    public VideoDataRepository(VideoSourceFactory videoSourceFactory) {
        mLocal = videoSourceFactory.createLocalSource();
        mRemote = videoSourceFactory.createRemoteSource();
    }

    @Override
    public Observable<CategoryModel> getCategory() {

        Observable<CategoryModel> localObservable = mLocal.getCategory();
        Observable<CategoryModel> remoteObservable = mRemote.getCategory();

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
    public Observable<ResListModel> getResourceList(int resId, int tag, int start, int num) {

        Observable<ResListModel> localObservable = mLocal.getResourceList(resId, tag, start, num);
        Observable<ResListModel> remoteObservable = mRemote.getResourceList(resId, tag, start, num);

        return Observable
                .concat(localObservable, remoteObservable)
                .takeFirst(new Func1<ResListModel, Boolean>() {
                    @Override
                    public Boolean call(ResListModel model) {
                        return model != null;
                    }
                });
    }

    @Override
    public Observable<ResRetailsModel> getResRetails(String path) {

        Observable<ResRetailsModel> localObservable = mLocal.getResRetails(path);
        Observable<ResRetailsModel> remoteObservable = mRemote.getResRetails(path);

        return Observable
                .concat(localObservable, remoteObservable)
                .takeFirst(new Func1<ResRetailsModel, Boolean>() {
                    @Override
                    public Boolean call(ResRetailsModel model) {
                        return model != null;
                    }
                });
    }
}
