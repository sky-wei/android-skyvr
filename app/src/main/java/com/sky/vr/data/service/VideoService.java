package com.sky.vr.data.service;

import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sky on 16-9-29.
 */

public interface VideoService {

    @GET("1/tags_list.js")
    Observable<Tags> getCategory();

    @GET("1/category/catinfo/{resId}-start{start}-num{num}.js")
    Observable<TagsResource> getCategoryCatInfo(
            @Path("resId") int resId, @Path("start") int start, @Path("num") int num);

    @GET("1/tags_resource-type{resId}-tag{tag}-start{start}-num{num}.js")
    Observable<TagsResource> getTagsResource(
            @Path("resId") int resId, @Path("tag") int tag, @Path("start") int start, @Path("num") int num);
}
