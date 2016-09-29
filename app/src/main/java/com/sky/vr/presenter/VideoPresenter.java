package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.data.source.VideoRepository;
import com.sky.vr.data.source.remote.VideoRemoteDataSource;
import com.sky.vr.event.VideoEvent;

/**
 * Created by sky on 16-9-28.
 */

public class VideoPresenter extends VRBasePresenter<VideoEvent> implements VideoContract.Presenter {

    private VideoContract.View mView;
    private VideoRepository mRepository;

    public VideoPresenter(Context context, VideoContract.View view) {
        super(context);
        mView = view;
        view.setPresenter(this);
        mRepository = new VideoRepository(null, new VideoRemoteDataSource());
    }

    @Override
    public void onMainThreadEvent(VideoEvent event) {

    }

    @Override
    public void loadTables() {

        mView.showLoading();

        mRepository.getVideCategory(new VideoDataSource.LoadCategoryCallback() {
            @Override
            public void onCategoryLoaded(Tags tags) {


            }

            @Override
            public void onFailure(String msg, Throwable tr) {

            }
        });

    }
}
