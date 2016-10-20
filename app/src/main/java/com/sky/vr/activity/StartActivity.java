package com.sky.vr.activity;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;

/**
 * Created by sky on 16-9-27.
 */
public class StartActivity extends VRBaseActivity {

    @Override
    protected void initView() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }
}
