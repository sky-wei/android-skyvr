package com.sky.vr.base;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by sky on 16-9-29.
 */

public abstract class VRBasePresenter<T> implements BasePresenter {

    private Context mContext;

    public VRBasePresenter(Context context) {
        mContext = context;
        init();
    }

    public Context getContext() {
        return mContext;
    }

    public void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThreadEvent(T event) {
    }
}
