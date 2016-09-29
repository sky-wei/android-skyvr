package com.sky.vr.service;

import com.sky.vr.data.mojing.Tags;

import retrofit2.Call;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoService {

    @POST("http://res.static.mojing.cn/160630-1-1-1/android/zh/1/tags_list.js")
    Observable<Tags> getCategory();
}
