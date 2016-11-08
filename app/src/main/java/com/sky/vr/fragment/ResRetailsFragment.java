package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sky.android.common.utils.DisplayUtils;
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

public class ResRetailsFragment extends PresenterFragment<ResRetailsPresenter>
        implements ResRetailsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_evaluation)
    TextView tv_evaluation;

    @BindView(R.id.tv_source)
    TextView tv_source;

    @BindView(R.id.btn_play)
    Button btn_play;

    @BindView(R.id.btn_download)
    Button btn_download;

    @BindView(R.id.iv_desc)
    TextView iv_desc;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_res_retails, container, false);
    }

    @Override
    protected void initView(View view, Bundle args) {
        ButterKnife.bind(this, view);

        refresh_layout.setColorSchemeResources(R.color.colorPrimary);
        refresh_layout.setOnRefreshListener(this);

        mPresenter = new ResRetailsPresenter(getContext(), args, this);

        forceRefreshing();
        mPresenter.loadResRetails();
    }

    @Override
    public void setResRetails(ResRetailsModel model) {

        // 取消更新
        refresh_layout.setRefreshing(false);

        if (model == null) return ;

        Glide.with(getContext())
                .load(model.getScreenshot().get(0))
                .crossFade()
                .into(iv_image);

        tv_title.setText(model.getTitle());
        tv_evaluation.setText(model.getScore());
        tv_source.setText(getString(R.string.source_x, model.getSource()));
        iv_desc.setText(model.getDesc());
    }

    @OnClick({R.id.btn_play, R.id.btn_download})
    public void onClick(View view) {
        mPresenter.playResource();
    }

    @Override
    public void onRefresh() {

        // 加载详情
        mPresenter.loadResRetails();
    }

    public void forceRefreshing() {

        // 显示加载进度
        refresh_layout.setProgressViewOffset(false, 0,
                DisplayUtils.dip2px(getContext(), 60));
        refresh_layout.setRefreshing(true);
    }
}
