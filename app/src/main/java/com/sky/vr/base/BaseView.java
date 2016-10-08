package com.sky.vr.base;

/**
 * Created by sky on 16-9-28.
 */
public interface BaseView<T> {

    void showLoading();

    void cancelLoading();

    void showMessage(String msg);

    void setPresenter(T presenter);
}
