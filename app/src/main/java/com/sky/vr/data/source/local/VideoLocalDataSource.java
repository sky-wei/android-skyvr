package com.sky.vr.data.source.local;

import android.content.Context;

import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;

import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public class VideoLocalDataSource implements VideoDataSource {

    private Context mContext;

    public VideoLocalDataSource(Context context) {
        mContext = context;
    }

    @Override
    public Observable<Tags> getCategory() {

        return null;
    }

    @Override
    public void saveCategory(Tags tags) {

        if (tags == null) return ;


    }

    @Override
    public Observable<TagsResource> getTagsResource(int resId, int tag, int start, int num) {
        return null;
    }
}
