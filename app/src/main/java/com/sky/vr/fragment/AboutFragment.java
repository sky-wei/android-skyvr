package com.sky.vr.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.vr.R;
import com.sky.vr.base.VRBaseFragment;
import com.sky.vr.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sky on 16-10-9.
 */
public class AboutFragment extends VRBaseFragment {

    @BindView(R.id.tv_version)
    TextView tv_version;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        // 设置版本名称
        tv_version.setText(getString(R.string.version_x,
                AppUtil.getAppVersionName(getContext())));
    }


    @OnClick(R.id.tv_source)
    public void onClick(View view) {

        Uri uri = Uri.parse("https://github.com/jingcai-wei/android-skyvr");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
