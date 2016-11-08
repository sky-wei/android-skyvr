package com.sky.vr.base;

import android.widget.Toast;

import com.sky.vr.R;
import com.sky.vr.view.LoadingDialog;

/**
 * Created by sky on 16-9-28.
 */

public abstract class PresenterFragment<T extends BasePresenter> extends VRBaseFragment implements BaseView {

    protected T mPresenter;
    private LoadingDialog mLoadingDialog;
    private boolean mShowLoading;

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

        if (!mShowLoading) return ;

        if (mLoadingDialog != null) {
            // 释放
            mLoadingDialog.dismiss();
        }

        mLoadingDialog = new LoadingDialog(getContext());
        mLoadingDialog.setTipText(R.string.loading);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
    }

    @Override
    public void cancelLoading() {

        if (!mShowLoading) return ;

        // 隐藏
        mLoadingDialog.dismiss();
        mLoadingDialog = null;
    }

    @Override
    public void showMessage(String msg) {
        // 显示提示信息
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isShowLoading() {
        return mShowLoading;
    }

    public void setShowLoading(boolean showLoading) {
        mShowLoading = showLoading;
    }

    public void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter() {
        return mPresenter;
    }
}
