package com.sky.vr.data.source.disk;

import android.content.Context;

import com.sky.vr.data.cache.VideoCache;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.data.source.VideoDataSource;

import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public class DiskVideoDataSource implements VideoDataSource {

    private Context mContext;
    private VideoCache mCache;

    public DiskVideoDataSource(Context context, VideoCache cache) {
        mContext = context;
        mCache = cache;
    }


    @Override
    public Observable<CategoryModel> getCategory() {
        return mCache.getCategory();
    }

    @Override
    public Observable<ResourceModel> getTagsResource(int resId, int tag, int start, int num) {
        return Observable.just(null);
    }
}
