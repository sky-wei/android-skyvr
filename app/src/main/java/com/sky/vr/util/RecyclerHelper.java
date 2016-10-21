package com.sky.vr.util;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.sky.android.common.utils.DisplayUtils;

/**
 * Created by sky on 16-10-21.
 */

public class RecyclerHelper extends RecyclerView.OnScrollListener implements
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private OnCallback mOnCallback;

    public RecyclerHelper(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, OnCallback onCallback) {
        mRefreshLayout = refreshLayout;
        mRecyclerView = recyclerView;
        mOnCallback = onCallback;
        mRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(this);
    }

    public OnCallback getOnCallback() {
        return mOnCallback;
    }

    public void setOnCallback(OnCallback onCallback) {
        mOnCallback = onCallback;
    }

    @Override
    public void onRefresh() {
        mOnCallback.onRefresh();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && !mRefreshLayout.isRefreshing()
                && isSlideToBottom(recyclerView)) {

            // 加载更多
            mRefreshLayout.setRefreshing(true);
            mOnCallback.onLoadMore();
        }
    }

    public boolean isRefreshing() {
        return mRefreshLayout.isRefreshing();
    }

    public void forceRefreshing() {

        if (isRefreshing()) return ;

        // 显示加载进度
        mRefreshLayout.setProgressViewOffset(false, 0,
                DisplayUtils.dip2px(mRefreshLayout.getContext(), 60));
        mRefreshLayout.setRefreshing(true);
    }

    public void cancelRefreshing() {

        if (!isRefreshing()) return ;

        mRefreshLayout.setRefreshing(false);
    }

    public boolean isSlideToBottom(RecyclerView recyclerView) {

        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public interface OnCallback {

        void onRefresh();

        void onLoadMore();
    }
}
