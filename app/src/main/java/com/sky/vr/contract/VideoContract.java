package com.sky.vr.contract;

import com.sky.vr.base.BasePresenter;
import com.sky.vr.base.BaseView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by sky on 16-9-28.
 */

public interface VideoContract {

    interface View extends BaseView<Presenter> {

        void showLoading();
    }

    interface Presenter<T> extends BasePresenter {

        void loadTables();
    }
}
