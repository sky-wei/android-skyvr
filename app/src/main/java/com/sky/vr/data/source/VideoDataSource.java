package com.sky.vr.data.source;

import com.sky.vr.data.mojing.Tags;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoDataSource {

    interface LoadCategoryCallback {

        void onCategoryLoaded(Tags tags);

//        void onDataNotAvailable();

        void onFailure(String msg, Throwable tr);
    }

    void getVideCategory(LoadCategoryCallback callback);
}
