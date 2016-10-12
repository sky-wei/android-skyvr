package com.sky.vr.data.source;

import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResourceModel;

import rx.Observable;

/**
 * Created by sky on 16-10-12.
 */

public interface VideoDataSource {

    Observable<CategoryModel> getCategory();

    void saveCategory(CategoryModel model);

    Observable<ResourceModel> getTagsResource(int resId, int tag, int start, int num);
}
