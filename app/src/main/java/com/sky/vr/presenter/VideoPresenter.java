package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.VideoContract;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.source.VideoDataSource;
import com.sky.vr.data.source.VideoRepository;
import com.sky.vr.data.source.remote.VideoRemoteDataSource;
import com.sky.vr.event.VideoEvent;
import com.sky.vr.model.CategoryModel;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

        mRepository.getVideCategory()
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Tags, Observable<List<CategoryModel>>>() {
                    @Override
                    public Observable<List<CategoryModel>> call(Tags tags) {
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CategoryModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CategoryModel> categoryModels) {

                    }
                });

    }
}
