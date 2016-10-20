package com.sky.vr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sky.android.common.base.BaseActivity;

/**
 * Created by sky on 16-9-27.
 */
public abstract class VRBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    protected abstract void initView();
}
