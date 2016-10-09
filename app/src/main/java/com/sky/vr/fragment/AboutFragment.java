package com.sky.vr.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseFragment;
import com.sky.vr.util.AppUtil;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sky on 16-10-9.
 */
@EFragment(R.layout.fragment_about)
public class AboutFragment extends VRBaseFragment {

    @ViewById(R.id.tv_version)
    TextView tv_version;

    @Override
    public void initView() {

        // 设置版本名称
        tv_version.setText(getString(R.string.version_x,
                AppUtil.getAppVersionName(getContext())));
    }

    @Click(R.id.tv_source)
    public void onClick(View view) {

        Uri uri = Uri.parse("https://github.com/jingcai-wei/android-skyvr");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
