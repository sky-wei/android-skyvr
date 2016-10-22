package com.sky.vr.data.service;

import com.sky.vr.data.mojing.ResRetails;
import com.sky.vr.data.mojing.Result;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.mojing.TagsResource;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoService {

    @GET("1/tags_list.js")
    Observable<Result<Tags>> getCategory();

    @GET("1/category/catinfo/{resId}-start{start}-num{num}.js")
    Observable<Result<TagsResource>> getCategoryCatInfo(
            @Path("resId") int resId, @Path("start") int start, @Path("num") int num);

    @GET("1/tags_resource-type{resId}-tag{tag}-start{start}-num{num}.js")
    Observable<Result<TagsResource>> getTagsResource(
            @Path("resId") int resId, @Path("tag") int tag, @Path("start") int start, @Path("num") int num);

    @GET("{path}")
    Observable<Result<ResRetails>> getResRetails(@Path("path") String path);
}
