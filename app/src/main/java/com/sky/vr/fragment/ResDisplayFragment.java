package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sky.android.common.interfaces.OnItemEventListener;
import com.sky.android.common.utils.DisplayUtils;
import com.sky.vr.R;
import com.sky.vr.adapter.ResDisplayAdapter;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.presenter.ResDispalyPresenter;
import com.sky.vr.util.RecyclerHelper;
import com.sky.vr.util.decoration.GridSpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sky on 16-9-27.
 */
public class ResDisplayFragment extends PresenterFragment<VideoContract.Presenter>
        implements VideoContract.View, OnItemEventListener, RecyclerHelper.OnCallback {

    @BindView(R.id.swip_refresh_layout)
    SwipeRefreshLayout swip_refresh_layout;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ResDisplayAdapter mViewAdapter;
    private RecyclerHelper mHelper;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_res_display, container, false);
    }

    @Override
    protected void initView(View view, Bundle args) {
        ButterKnife.bind(this, view);

        mViewAdapter = new ResDisplayAdapter(getContext());
        mViewAdapter.setOnItemEventListener(this);

        swip_refresh_layout.setColorSchemeResources(R.color.colorPrimary);

        recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(2, DisplayUtils.dip2px(getContext(), 8), true));
        recycler_view.setHasFixedSize(true);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mViewAdapter);

        // 刷新助手类
        mHelper = new RecyclerHelper(swip_refresh_layout, recycler_view, this);
        mHelper.forceRefreshing();

        // 初始化
        mPresenter = new ResDispalyPresenter(getContext(), getArguments(), this);
        mPresenter.loadTagsResource();
    }

    @Override
    public void showLoading() {
//        super.showLoading();
    }

    @Override
    public void cancelLoading() {
        mHelper.cancelRefreshing();
    }

    @Override
    public void setTagsResource(List<ResourceModel.Resource> resources) {
        // 设置内容
        mViewAdapter.setItems(resources);
        mViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemEvent(int event, View view, int position, Object... args) {
        Toast.makeText(getContext(), "功能待续...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        // 重新加载数据
        mPresenter.loadTagsResource();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMoreTagsResource();
    }
}
