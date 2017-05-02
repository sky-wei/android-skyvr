package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.android.common.interfaces.OnItemEventListener;
import com.sky.android.common.utils.DisplayUtils;
import com.sky.vr.R;
import com.sky.vr.activity.RetailsActivity;
import com.sky.vr.adapter.ResListAdapter;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.ResListContract;
import com.sky.vr.data.model.ResListModel;
import com.sky.vr.presenter.ResListPresenter;
import com.sky.vr.util.RecyclerHelper;
import com.sky.vr.util.decoration.GridSpacingItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sky on 16-9-27.
 */
public class ResListFragment extends PresenterFragment<ResListContract.Presenter>
        implements ResListContract.View, OnItemEventListener, RecyclerHelper.OnCallback {

    @BindView(R.id.swip_refresh_layout)
    SwipeRefreshLayout swip_refresh_layout;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ResListAdapter mViewAdapter;
    private RecyclerHelper mHelper;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_res_list, container, false);
    }

    @Override
    protected void initView(View view, Bundle args) {
        ButterKnife.bind(this, view);

        mViewAdapter = new ResListAdapter(getContext());
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
        mPresenter = new ResListPresenter(getContext(), getArguments(), this);
        mPresenter.loadTagsResource();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void cancelLoading() {
        mHelper.cancelRefreshing();
    }

    @Override
    public void setTagsResource(List<ResListModel.Resource> resources) {
        // 设置内容
        mViewAdapter.setItems(resources);
        mViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void seeDetails(int type, ResListModel.Resource resource) {

        if (resource == null) return ;

        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putSerializable("resource", resource);

        RetailsActivity.startRetailsActivity(
                getContext(), "返回",
                ResRetailsFragment.class.getName(), args);
    }

    @Override
    public void onItemEvent(int event, View view, int position, Object... args) {
        // 处理事件
        mPresenter.onItemEvent(position);
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
