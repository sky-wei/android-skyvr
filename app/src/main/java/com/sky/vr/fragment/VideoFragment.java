package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.sky.vr.R;
import com.sky.vr.base.VRBaseFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.event.Event;
import com.sky.vr.event.VideoEvent;
import com.sky.vr.presenter.VideoPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by sky on 16-9-28.
 */
@EFragment(R.layout.fragment_video)
public class VideoFragment extends VRBaseFragment implements VideoContract.View {

    @ViewById(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager viewPager;

    @ViewById(R.id.test)
    Button test;

    private VideoContract.Presenter mPresenter;

    @Override
    public void initView() {

//        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
//                getChildFragmentManager(), FragmentPagerItems.with(getContext())
//                .add("Test1", PageFragment_.class)
//                .add("Test2", PageFragment_.class)
//                .create());

//        viewPager.setAdapter(adapter);

        smartTabLayout.setViewPager(viewPager);

        // 初始化
        mPresenter = new VideoPresenter(getContext(), this);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(Event.buildEvent(VideoEvent.class, 1));
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //    @Override
//    public void onMainThreadEvent(VideoEvent event) {
//        super.onMainThreadEvent(event);
//
//        if (mPresenter != null) mPresenter.mainThreadEvent(event);
//    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }
}
