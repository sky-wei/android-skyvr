package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.contract.VideoContract;
import com.sky.vr.event.VideoEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sky on 16-9-28.
 */

public class VideoPresenter implements VideoContract.Presenter<VideoEvent> {

    private Context mContext;
    private VideoContract.View mView;

    public VideoPresenter(Context context, VideoContract.View view) {
        mContext = context;
        mView = view;
        view.setPresenter(this);
    }


    @Override
    public void registerEvent() {
        EventBus.getDefault().register(this);
    }


    @Override
    public void mainThreadEvent(VideoEvent event) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + event);
    }

    @Override
    public void unregisterEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadTables() {

    }
}
