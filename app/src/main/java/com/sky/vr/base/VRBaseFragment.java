package com.sky.vr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sky.android.common.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by sky on 16-9-28.
 */
@EFragment
public abstract class VRBaseFragment extends BaseFragment {

    @AfterViews
    public abstract void initView();

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMainThreadEvent(T event) {
//
//    }
}
