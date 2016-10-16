package com.sky.vr.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.sky.android.common.interfaces.OnItemEventListener;
import com.sky.vr.R;
import com.sky.vr.adapter.ResDisplayAdapter;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.model.ResourceModel;
import com.sky.vr.presenter.ResDispalyPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by sky on 16-9-27.
 */
@EFragment(R.layout.fragment_res_display)
public class ResDisplayFragment extends PresenterFragment<VideoContract.Presenter>
        implements VideoContract.View, OnItemEventListener {

    @ViewById(R.id.recycler_view)
    public RecyclerView recycler_view;

    private ResDisplayAdapter mViewAdapter;

    @Override
    public void initView() {

        mViewAdapter = new ResDisplayAdapter(getContext());
        mViewAdapter.setOnItemEventListener(this);
        recycler_view.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recycler_view.setAdapter(mViewAdapter);

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
//        super.cancelLoading();
    }

    @Override
    public void appendTagsResource(List<ResourceModel.Resource> resources) {
        // 设置内容
        mViewAdapter.setItems(resources);
        mViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemEvent(int event, View view, int position, Object... args) {
        Toast.makeText(getContext(), "功能待续...", Toast.LENGTH_SHORT).show();
    }
}
