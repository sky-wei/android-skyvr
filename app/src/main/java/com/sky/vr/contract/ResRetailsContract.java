package com.sky.vr.contract;

import com.sky.vr.base.BasePresenter;
import com.sky.vr.base.BaseView;
import com.sky.vr.data.model.ResRetailsModel;

/**
 * Created by sky on 16-10-22.
 */

public interface ResRetailsContract {

    interface View extends BaseView {

        void setResRetails(ResRetailsModel model);
    }

    interface Presenter extends BasePresenter {

        void loadResRetails();

        void playResource();
    }
}
