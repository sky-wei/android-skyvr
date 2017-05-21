package com.sky.vr.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sky on 16-10-9.
 */
public class CommonActivity extends VRBaseActivity {

    public static final String TITLE = "title";
    public static final String F_NAME = "fName";
    public static final String SUPPORT_FRAGMENT = "supportFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.app_bar_frame;
    }

    @Override
    protected void initView(Intent intent) {

        int title = intent.getIntExtra(TITLE, R.string.app_name);
        String fName = intent.getStringExtra(F_NAME);
        boolean mSupportFragment = intent.getBooleanExtra(SUPPORT_FRAGMENT, true);

        // 设置ActionBar
        setSupportActionBar(toolbar, title, true);

        if (mSupportFragment) {
            // SupportFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = Fragment.instantiate(this, fName);
            fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
            return ;
        }

        // Fragment
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment fragment = android.app.Fragment.instantiate(this, fName);
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }
}
