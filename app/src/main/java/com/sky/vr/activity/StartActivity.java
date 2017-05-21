package com.sky.vr.activity;

import android.content.Intent;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;
import com.sky.vr.util.ActivityManager;

/**
 * Created by sky on 16-9-27.
 */
public class StartActivity extends VRBaseActivity {

    @Override
    protected int getLayoutId() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_start;
    }

    @Override
    protected void initView(Intent intent) {
        ActivityManager.startActivity(getContext(), MainActivity.class);
        finish();
    }
}
