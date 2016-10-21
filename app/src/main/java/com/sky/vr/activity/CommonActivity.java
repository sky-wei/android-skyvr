package com.sky.vr.activity;

import android.content.Context;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int mTitle;
    private String mFname;
    private boolean mSupportFragment;

    public static void startCommonActivity(Context context, int title, String fname) {
        startCommonActivity(context, title, fname, true);
    }

    public static void startCommonActivity(Context context, int title, String fname, boolean supportFragment) {

        Intent intent = new Intent(context, CommonActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("fname", fname);
        intent.putExtra("supportFragment", supportFragment);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.app_bar_frame);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView(Intent intent) {

        mTitle = intent.getIntExtra("title", R.string.app_name);
        mFname = intent.getStringExtra("fname");
        mSupportFragment = intent.getBooleanExtra("supportFragment", true);

        // 设置ActionBar
        setSupportActionBar(toolbar, mTitle, true);

        if (mSupportFragment) {
            // SupportFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = Fragment.instantiate(this, mFname);
            fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
            return ;
        }

        // Fragment
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.Fragment fragment = android.app.Fragment.instantiate(this, mFname);
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();
    }
}
