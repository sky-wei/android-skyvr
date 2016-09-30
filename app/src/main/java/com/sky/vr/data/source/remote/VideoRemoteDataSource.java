package com.sky.vr.data.source.remote;

import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.service.VideoService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRemoteDataSource implements VideoDataSource {

    @Override
    public Observable<Tags> getVideCategory() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoService videoService = retrofit.create(VideoService.class);

        return videoService.getCategory();
    }
}
