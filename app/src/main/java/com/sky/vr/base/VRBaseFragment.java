package com.sky.vr.base;

import com.sky.android.common.base.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Created by sky on 16-9-28.
 */
@EFragment
public abstract class VRBaseFragment extends BaseFragment {

    @AfterViews
    public abstract void initView();
}
