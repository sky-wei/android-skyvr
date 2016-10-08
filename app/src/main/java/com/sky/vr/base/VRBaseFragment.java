package com.sky.vr.base;

import android.widget.Toast;

import com.sky.android.common.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by sky on 16-9-28.
 */
@EFragment
public abstract class VRBaseFragment<T extends BasePresenter> extends BaseFragment implements BaseView<T> {

    protected T mPresenter;

    @Override
    public void onResume() {
        super.onResume();

        if (mPresenter != null) {
            mPresenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mPresenter != null) {
            mPresenter.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showMessage(String msg) {
        // 显示提示信息
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter() {
        return mPresenter;
    }

    @AfterViews
    public abstract void initView();
}
