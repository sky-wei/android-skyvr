package com.sky.vr.base;

import com.sky.android.common.utils.Alog;

import rx.Subscriber;

/**
 * Created by sky on 16-10-16.
 */

public abstract class BaseSubcriber<T> extends Subscriber<T> {

    private static final String TAG = BaseSubcriber.class.getSimpleName();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Alog.e(TAG, "数据处理异常", e);

//        if (e instanceof IOException) {
//
//            // IO异常
//
//        }
    }

    @Override
    public void onNext(T model) {

    }
}
