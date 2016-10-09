package com.sky.vr.data.source;

import com.sky.vr.data.mojing.Tags;

import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoDataSource {

    Observable<Tags> getVideCategory();

    void saveVideCategory(Tags tags);
}
