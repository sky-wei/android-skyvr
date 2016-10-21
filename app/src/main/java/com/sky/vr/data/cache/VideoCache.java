package com.sky.vr.data.cache;

import com.sky.vr.data.model.CategoryModel;

import rx.Observable;

/**
 * Created by sky on 16-10-21.
 */

public interface VideoCache {

    Observable<CategoryModel> getCategory();

    void saveCategory(CategoryModel model);
}
