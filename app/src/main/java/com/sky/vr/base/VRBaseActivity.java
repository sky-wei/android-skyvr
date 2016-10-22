package com.sky.vr.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.sky.android.common.base.BaseActivity;

/**
 * Created by sky on 16-9-27.
 */
public abstract class VRBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置
        setContentView();

        // 初始化
        initView(getIntent());
    }

    protected abstract void setContentView();

    protected abstract void initView(Intent intent);

    protected void setSupportActionBar(Toolbar toolbar, int title, boolean homeAsUp) {
      setSupportActionBar(toolbar, getString(title), homeAsUp);
    }

    protected void setSupportActionBar(Toolbar toolbar, String title, boolean homeAsUp) {

        if (toolbar == null) return ;

        setSupportActionBar(toolbar);

        // 设置ActivonBar
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(homeAsUp);
    }
}
