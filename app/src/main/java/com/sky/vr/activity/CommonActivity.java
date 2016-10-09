package com.sky.vr.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sky on 16-10-9.
 */
@EActivity(R.layout.app_bar_frame)
public class CommonActivity extends VRBaseActivity {

    @Extra
    int title;

    @Extra
    String fname;

    @Extra
    boolean supportFragment = true;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initView() {

        setSupportActionBar(toolbar);

        // 设置ActivonBar
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (supportFragment) {

            // SupportFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = Fragment.instantiate(this, fname);
            fragmentManager.beginTransaction().add(R.id.frame, fragment).commit();
            return ;
        }

        // Fragment
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment fragment = android.app.Fragment.instantiate(this, fname);
        fragmentManager.beginTransaction().add(R.id.frame, fragment).commit();
    }
}
