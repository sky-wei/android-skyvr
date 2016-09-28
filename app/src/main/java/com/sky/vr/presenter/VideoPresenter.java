package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.contract.VideoContract;
import com.sky.vr.event.VideoEvent;

/**
 * Created by sky on 16-9-28.
 */

public class VideoPresenter implements VideoContract.Presenter<VideoEvent> {

    private Context mContext;
    private VideoContract.View mView;

    public VideoPresenter(Context context, VideoContract.View view) {
        mContext = context;
        mView = view;
    }


    @Override
    public void registerEvent() {

    }


    @Override
    public void mainThreadEvent(VideoEvent event) {

    }

    @Override
    public void unregisterEvent() {

    }
}
