package com.sky.vr.data.cache.impl;

import com.sky.vr.data.cache.CacheManager;
import com.sky.vr.data.cache.VideoCache;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResRetailsModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sky on 16-10-21.
 */

public class VideoCacheImpl implements VideoCache {

    private CacheManager mCacheManager;
    private String mCategoryKey;

    public VideoCacheImpl(CacheManager cacheManager) {
        mCacheManager = cacheManager;
        mCategoryKey = mCacheManager.buildKey(VideoCacheImpl.class.getName() + ":getCategory()");
    }

    @Override
    public Observable<CategoryModel> getCategory() {

        return Observable.create(new Observable.OnSubscribe<CategoryModel>() {
            @Override
            public void call(Subscriber<? super CategoryModel> subscriber) {

                handler(subscriber, mCacheManager.get(mCategoryKey, CategoryModel.class));
            }
        });
    }

    @Override
    public void saveCategory(CategoryModel model) {
        mCacheManager.put(mCategoryKey, model);
    }

    @Override
    public Observable<ResRetailsModel> getResRetails(final String path) {

        return Observable.create(new Observable.OnSubscribe<ResRetailsModel>() {
            @Override
            public void call(Subscriber<? super ResRetailsModel> subscriber) {

                String key = mCacheManager.buildKey(path);

                handler(subscriber, mCacheManager.get(key, ResRetailsModel.class));
            }
        });
    }

    @Override
    public void saveResRetails(String path, ResRetailsModel model) {

        String key = mCacheManager.buildKey(path);

        mCacheManager.put(key, model);
    }

    private <T> void handler(Subscriber<? super T> subscriber, T model) {

        try {
            // 处理下一步
            subscriber.onNext(model);
        } catch (Exception e) {
            // 出错了
            subscriber.onError(e);
            return ;
        }
        // 完成
        subscriber.onCompleted();
    }
}
