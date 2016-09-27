package com.sky.vr.base;

import com.sky.android.common.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by sky on 16-9-27.
 */
@EActivity
public abstract class VRBaseActivity extends BaseActivity {

    @AfterViews
    public abstract void initView();
}
