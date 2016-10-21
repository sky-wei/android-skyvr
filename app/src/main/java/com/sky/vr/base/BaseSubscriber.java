package com.sky.vr.base;

import com.sky.android.common.utils.Alog;
import com.sky.vr.data.DataException;

import rx.Subscriber;

/**
 * Created by sky on 16-10-16.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private static final String TAG = BaseSubscriber.class.getSimpleName();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof DataException) {
            // 自己定义异常处理
            DataException ex = (DataException)e;
            onError(ex.getCode(), ex.getMessage(), e);
            return ;
        }

        // 其他异常
        onError(DataException.CODE_UNKNOWN, e.getMessage(), e);
    }

    private void onError(int code, String msg, Throwable e) {

        boolean handler = onError(code, msg);

        if (handler) return ;

        Alog.e(TAG, "数据处理异常", e);
    }

    protected boolean onError(int code, String msg) {
        return false;
    }

    @Override
    public void onNext(T model) {

    }
}
