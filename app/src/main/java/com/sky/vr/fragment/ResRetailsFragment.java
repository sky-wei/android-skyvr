package com.sky.vr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sky.vr.R;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.ResRetailsContract;
import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.presenter.ResRetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sky on 16-10-21.
 */

public class ResRetailsFragment extends PresenterFragment<ResRetailsPresenter> implements ResRetailsContract.View {

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @BindView(R.id.iv_desc)
    TextView iv_desc;

    @BindView(R.id.btn_test)
    Button btn_test;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_res_retails, container, false);
    }

    @Override
    protected void initView(View view, Bundle args) {
        ButterKnife.bind(this, view);

        mPresenter = new ResRetailsPresenter(getContext(), args, this);
        mPresenter.loadResRetails();
    }

    @Override
    public void setResRetails(ResRetailsModel model) {

        if (model == null) return ;

        Glide.with(getContext())
                .load(model.getScreenshot().get(0))
                .crossFade()
                .into(iv_image);

        iv_desc.setText(model.getDesc());
    }

    @OnClick(R.id.btn_test)
    void onClick(View view) {
        mPresenter.playResource();
    }
}
