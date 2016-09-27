package com.sky.vr.activity;

import android.content.Intent;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

/**
 * Created by sky on 16-9-27.
 */
@Fullscreen
@EActivity(R.layout.activity_start)
public class StartActivity extends VRBaseActivity {

    @Override
    public void initView() {

        MainActivity_
                .intent(getContext())
                .flags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .start();
    }
}
