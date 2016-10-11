package com.sky.vr.data.source.remote;

import com.sky.vr.app.VRConfig;
import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.service.VideoService;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRemoteDataSource implements VideoDataSource {

    @Override
    public Tags getCategory() throws IOException {

        VideoService videoService = buildVideoService();

        Response<Tags> response =  videoService.getCategory().execute();

        return response.body();
    }

    @Override
    public void saveCategory(Tags tags) {
        // 使用也不做
    }

    @Override
    public Observable<TagsResource> getTagsResource(int resId, int tag, int start, int num) {

        VideoService videoService = buildVideoService();

        if (resId == 9999) {
            // 所有信息
            return videoService.getCategoryCatInfo(resId, start, num);
        }
        // 指定分类信息
        return videoService.getTagsResource(resId, tag, start, num);
    }

    private VideoService buildVideoService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VRConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(VideoService.class);
    }
}
