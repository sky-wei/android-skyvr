package com.sky.vr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.android.common.base.BaseFragment;
import com.sky.vr.R;

/**
 * Created by sky on 16-9-27.
 */

public class PageFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_page, container, false);
    }
}
