package com.sky.vr.fragment;

import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.sky.vr.R;
import com.sky.vr.base.VRBaseFragment;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.model.CategoryModel;
import com.sky.vr.presenter.VideoPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by sky on 16-9-28.
 */
@EFragment(R.layout.fragment_video)
public class VideoFragment extends VRBaseFragment<VideoContract.Presenter> implements VideoContract.View {

    @ViewById(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void initView() {

        // 初始化
        mPresenter = new VideoPresenter(getContext(), this);
        mPresenter.loadTables();
    }

    @Override
    public void setTables(List<CategoryModel.SubCategory> subCategories) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getContext());

        for (int i = 0; i < subCategories.size(); i++) {

            CategoryModel.SubCategory subCategory = subCategories.get(i);
            creator.add(subCategory.getName(), SubVideoFragment_.class);
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), creator.create());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }
}
