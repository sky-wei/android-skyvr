package com.sky.vr.contract;

import com.sky.vr.base.BasePresenter;
import com.sky.vr.base.BaseView;
import com.sky.vr.model.CategoryModel;
import com.sky.vr.model.ResourceModel;

import java.util.List;

/**
 * Created by sky on 16-9-28.
 */

public interface VideoContract {

    interface View extends BaseView<Presenter> {

        void appendTagsResource(List<ResourceModel.Resource> resources);
    }

    interface Presenter extends BasePresenter {

        void loadTagsResource(int resId, int tag);
    }
}
