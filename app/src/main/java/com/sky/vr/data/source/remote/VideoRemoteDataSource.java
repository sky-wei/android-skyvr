package com.sky.vr.data.source.remote;

import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.service.VideoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRemoteDataSource implements VideoDataSource {

    @Override
    public void getVideCategory(final LoadCategoryCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoService videoService = retrofit.create(VideoService.class);

        videoService.getCategory().enqueue(new Callback<Tags>() {
            @Override
            public void onResponse(Call<Tags> call, Response<Tags> response) {
                // 数据回调
                callback.onCategoryLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Tags> call, Throwable t) {
                // 异常回调
                callback.onFailure(t.getMessage(), t);
            }
        });
    }
}
