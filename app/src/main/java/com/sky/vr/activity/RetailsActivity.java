package com.sky.vr.activity;

import android.content.Context;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startRetailsActivity(Context context, String title, String fname) {
        startRetailsActivity(context, title, fname, null);
    }

    public static void startRetailsActivity(Context context, String title, String fname, Bundle args) {

        Intent intent = new Intent(context, RetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("fname", fname);
        intent.putExtra("args", args);

        context.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_retails);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Intent intent) {

        String title = intent.getStringExtra("title");
        String fname = intent.getStringExtra("fname");
        Bundle args = intent.getBundleExtra("args");

        // 设置ActionBar
        setSupportActionBar(toolbar, title, true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = Fragment.instantiate(this, fname, args);
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }
}
