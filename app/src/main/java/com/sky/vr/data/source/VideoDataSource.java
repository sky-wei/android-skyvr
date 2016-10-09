package com.sky.vr.data.source;

import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;

import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoDataSource {

    Observable<Tags> getCategory();

    void saveCategory(Tags tags);

    Observable<TagsResource> getTagsResource(int resId, int tag, int start, int num);
}
