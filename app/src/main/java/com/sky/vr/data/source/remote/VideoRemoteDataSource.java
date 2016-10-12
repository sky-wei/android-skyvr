package com.sky.vr.data.source.remote;

import com.sky.vr.app.VRConfig;
import com.sky.vr.data.DataException;
import com.sky.vr.data.mapper.CategoryMapper;
import com.sky.vr.data.mapper.ResourceMapper;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.data.service.VideoService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by sky on 16-9-29.
 */

public class VideoRemoteDataSource extends BaseRemoteDataSource implements VideoDataSource {

    @Override
    public Observable<CategoryModel> getCategory() {

        VideoService videoService = buildVideoService();

        return videoService
                .getCategory()
                .map(new Func1<Tags, CategoryModel>() {
                    @Override
                    public CategoryModel call(Tags tags) {

                        CategoryMapper mapper = new CategoryMapper();

                        return mapper.transform(tags);
                    }
                });
    }

    @Override
    public void saveCategory(CategoryModel model) {
        // 使用也不做
    }

    @Override
    public Observable<ResourceModel> getTagsResource(int resId, int tag, int start, int num) {

        return getTagsResourceEx(resId, tag, start, num)
                .map(new Func1<TagsResource, ResourceModel>() {
                    @Override
                    public ResourceModel call(TagsResource tagsResource) {

                        ResourceMapper mapper = new ResourceMapper();

                        return mapper.transform(tagsResource);
                    }
                });
    }

    private Observable<TagsResource> getTagsResourceEx(int resId, int tag, int start, int num) {

        VideoService videoService = buildVideoService();

        if (resId == 9999) {
            // 所有信息
            return videoService.getCategoryCatInfo(resId, start, num);
        }
        // 指定分类信息
        return videoService.getTagsResource(resId, tag, start, num);
    }

    private VideoService buildVideoService() {
        return buildService(VideoService.class, VRConfig.BASE_URL);
    }
}
