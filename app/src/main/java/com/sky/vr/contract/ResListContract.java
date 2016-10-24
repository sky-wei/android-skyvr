package com.sky.vr.contract;

import com.sky.vr.base.BasePresenter;
import com.sky.vr.base.BaseView;
import com.sky.vr.data.model.ResListModel;

import java.util.List;

/**
 * Created by sky on 16-9-28.
 */

public interface ResListContract {

    interface View extends BaseView {

        void setTagsResource(List<ResListModel.Resource> resources);

        void seeDetails(int type, ResListModel.Resource resource);
    }

    interface Presenter extends BasePresenter {

        void loadTagsResource();

        void loadMoreTagsResource();

        void onItemEvent(int position);
    }
}
