package com.sky.vr.data.source.cloud;

import com.sky.vr.app.Constant;
import com.sky.vr.data.cache.VideoCache;
import com.sky.vr.data.mapper.MapperFactory;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResListModel;
import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.data.mojing.ResList;
import com.sky.vr.data.mojing.ResRetails;
import com.sky.vr.data.mojing.Result;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.service.VideoService;
import com.sky.vr.data.source.VideoDataSource;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sky on 16-9-29.
 */

public class CloudVideoDataSource extends CloudDataSource implements VideoDataSource {

    private VideoCache mCache;

    public CloudVideoDataSource(VideoCache cache) {
        mCache = cache;
    }

    @Override
    public Observable<CategoryModel> getCategory() {

        VideoService videoService = buildVideoService();

        return videoService
                .getCategory()
                .map(new Func1<Result<Tags>, CategoryModel>() {
                    @Override
                    public CategoryModel call(Result<Tags> result) {
                        return MapperFactory.createCategoryMapper().transform(result);
                    }
                })
                .doOnNext(new Action1<CategoryModel>() {
                    @Override
                    public void call(CategoryModel categoryModel) {

                        // 保存到缓存中
                        mCache.saveCategory(categoryModel);
                    }
                });
    }

    @Override
    public Observable<ResListModel> getResourceList(int resId, int tag, int start, int num) {

        return getTagsResourceEx(resId, tag, start, num)
                .map(new Func1<Result<ResList>, ResListModel>() {
                    @Override
                    public ResListModel call(Result<ResList> result) {
                        return MapperFactory.createResListMapper().transform(result);
                    }
                });
    }

    @Override
    public Observable<ResRetailsModel> getResRetails(final String path) {

        VideoService videoService = buildVideoService();

        return videoService.getResRetails(path)
                .map(new Func1<Result<ResRetails>, ResRetailsModel>() {
                    @Override
                    public ResRetailsModel call(Result<ResRetails> result) {
                        return MapperFactory.createResRetailsMapper().transform(result);
                    }
                })
                .doOnNext(new Action1<ResRetailsModel>() {
                    @Override
                    public void call(ResRetailsModel model) {

                        // 保存到缓存
                        mCache.saveResRetails(path, model);
                    }
                });
    }

    private Observable<Result<ResList>> getTagsResourceEx(int resId, int tag, int start, int num) {

        VideoService videoService = buildVideoService();

        if (resId == 9999) {
            // 所有信息
            return videoService.getCategoryCatInfo(resId, start, num);
        }
        // 指定分类信息
        return videoService.getTagsResource(resId, tag, start, num);
    }

    private VideoService buildVideoService() {
        return buildService(VideoService.class, Constant.Service.BASE_URL);
    }
}
