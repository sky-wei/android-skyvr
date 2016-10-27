package com.sky.vr.data.cache;

import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResRetailsModel;

import rx.Observable;

/**
 * Created by sky on 16-10-21.
 */

public interface VideoCache {

    /**
     * 获取分类列表
     * @return
     */
    Observable<CategoryModel> getCategory();

    /**
     * 保存分类列表
     * @param model
     */
    void saveCategory(CategoryModel model);


    /**
     * 获取资源详情信息
     * @param path
     * @return
     */
    Observable<ResRetailsModel> getResRetails(final String path);

    /**
     * 保存资源详情
     * @param path
     * @param model
     */
    void saveResRetails(String path, ResRetailsModel model);
}
