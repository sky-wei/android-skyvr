package com.sky.vr.fragment;

import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.sky.vr.R;
import com.sky.vr.base.VRBaseFragment;
import com.sky.vr.contract.VideoContract;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sky on 16-9-28.
 */
@EFragment(R.layout.fragment_video)
public class VideoFragment extends VRBaseFragment implements VideoContract.View {

    @ViewById(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager viewPager;

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
    }

    @Override
    public void setPresenter(VideoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }
}
