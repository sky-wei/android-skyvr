package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.source.VideoDataRepository;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.data.source.VideoSourceFactory;
import com.sky.vr.data.source.local.VideoLocalDataSource;
import com.sky.vr.data.source.remote.VideoRemoteDataSource;
import com.sky.vr.event.VideoEvent;
import com.sky.vr.data.mapper.ResourceMapper;
import com.sky.vr.data.model.ResourceModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 16-9-28.
 */

public class VideoPresenter extends VRBasePresenter<VideoEvent> implements VideoContract.Presenter {

    private VideoContract.View mView;
    private VideoDataRepository mRepository;

    public VideoPresenter(Context context, VideoContract.View view) {
        super(context);
        mView = view;
        view.setPresenter(this);
        mRepository = new VideoDataRepository(new VideoSourceFactory(context));
    }

    @Override
    public void onMainThreadEvent(VideoEvent event) {

    }

    @Override
    public void loadTagsResource(int resId, int tag) {

        mView.showLoading();

        mRepository.getTagsResource(resId, tag, 0, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResourceModel>() {
                    @Override
                    public void onCompleted() {
                        mView.cancelLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.cancelLoading();
                        mView.showMessage("加载数据失败");
                    }

                    @Override
                    public void onNext(ResourceModel model) {
                        // 暂时不分页
                        mView.appendTagsResource(model.getResources());
                    }
                });
    }
}
