package com.sky.vr.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.sky.android.common.interfaces.OnItemEventListener;
import com.sky.vr.R;
import com.sky.vr.adapter.ResDisplayAdapter;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.presenter.ResDispalyPresenter;
import com.sky.vr.util.decoration.GridSpacingItemDecoration;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by sky on 16-9-27.
 */
@EFragment(R.layout.fragment_res_display)
public class ResDisplayFragment extends PresenterFragment<VideoContract.Presenter>
        implements VideoContract.View, OnItemEventListener, SwipeRefreshLayout.OnRefreshListener {

    @ViewById(R.id.swip_refresh_layout)
    SwipeRefreshLayout swip_refresh_layout;

    @ViewById(R.id.recycler_view)
    RecyclerView recycler_view;

    private ResDisplayAdapter mViewAdapter;

    @Override
    public void initView() {

        mViewAdapter = new ResDisplayAdapter(getContext());
        mViewAdapter.setOnItemEventListener(this);

        swip_refresh_layout.setColorSchemeResources(R.color.colorPrimary);
        swip_refresh_layout.setOnRefreshListener(this);

        recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler_view.addItemDecoration(new GridSpacingItemDecoration(2, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()), true));
        recycler_view.setHasFixedSize(true);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mViewAdapter);

        // 初始化
        mPresenter = new ResDispalyPresenter(getContext(), getArguments(), this);
        mPresenter.loadTagsResource();
        forceRefreshing();
    }

    @Override
    public void showLoading() {
//        super.showLoading();
    }

    @Override
    public void cancelLoading() {
//        super.cancelLoading();
    }

    @Override
    public void setTagsResource(List<ResourceModel.Resource> resources) {
        // 设置内容
        mViewAdapter.setItems(resources);
        mViewAdapter.notifyDataSetChanged();

        cancelRefreshing();
    }

    @Override
    public void onItemEvent(int event, View view, int position, Object... args) {
        Toast.makeText(getContext(), "功能待续...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

        mPresenter.loadTagsResource();
    }

    protected void forceRefreshing() {

        if (swip_refresh_layout.isRefreshing()) return ;

        // 显示加载进度
        swip_refresh_layout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
        swip_refresh_layout.setRefreshing(true);
    }

    protected void cancelRefreshing() {

        if (!swip_refresh_layout.isRefreshing()) return ;

        swip_refresh_layout.setRefreshing(false);
    }
}
