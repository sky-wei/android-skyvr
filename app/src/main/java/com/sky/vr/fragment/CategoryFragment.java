package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.sky.vr.R;
import com.sky.vr.base.PresenterFragment;
import com.sky.vr.contract.CategoryContract;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.presenter.CategoryPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sky on 16-9-28.
 */
public class CategoryFragment extends PresenterFragment<CategoryContract.Presenter> implements CategoryContract.View {

    @BindView(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    protected void initView(View view, Bundle args) {
        ButterKnife.bind(this, view);

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

            creator.add(subCategory.getName(), ResListFragment.class, args);
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), creator.create());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }
}
