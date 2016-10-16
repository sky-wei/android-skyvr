package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.sky.vr.R;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.CategoryContract;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.presenter.CategoryPresenter;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by sky on 16-9-28.
 */
@EFragment(R.layout.fragment_category)
public class CategoryFragment extends PresenterFragment<CategoryContract.Presenter> implements CategoryContract.View {

    @ViewById(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @ViewById(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void initView() {

        // 初始化
        mPresenter = new CategoryPresenter(getContext(), getArguments(), this);
        mPresenter.loadCategory();
    }

    @Override
    public void setCategory(int type, int resId, List<CategoryModel.SubCategory> subCategories) {

        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getContext());

        for (int i = 0; i < subCategories.size(); i++) {

            CategoryModel.SubCategory subCategory = subCategories.get(i);

            Bundle args = new Bundle();
            args.putInt("type", type);
            args.putInt("resId", resId);
            args.putInt("tag", subCategory.getId());

            creator.add(subCategory.getName(), ResDisplayFragment_.class, args);
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), creator.create());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }
}
