package com.sky.vr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sky on 16-10-22.
 */

public class RetailsActivity extends VRBaseActivity {

    public static final String TITLE = "title";
    public static final String F_NAME = "fName";
    public static final String ARGS = "args";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retails;
    }

    @Override
    protected void initView(Intent intent) {

        String title = intent.getStringExtra(TITLE);
        String fName = intent.getStringExtra(F_NAME);
        Bundle args = intent.getBundleExtra(ARGS);

        // 设置ActionBar
        setSupportActionBar(toolbar, title, true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = Fragment.instantiate(this, fName, args);
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }
}
