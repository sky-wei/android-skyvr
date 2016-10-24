package com.sky.vr.data.source;

import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.data.model.ResListModel;

import rx.Observable;

/**
 * Created by sky on 16-10-12.
 */

public interface VideoDataSource {

    Observable<CategoryModel> getCategory();

    Observable<ResListModel> getResourceList(int resId, int tag, int start, int num);

    Observable<ResRetailsModel> getResRetails(String path);
}
