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
    protected void setContentView() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initView(Intent intent) {

        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);

        finish();
    }
}
