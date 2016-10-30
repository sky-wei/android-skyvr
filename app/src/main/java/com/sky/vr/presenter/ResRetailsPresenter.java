package com.sky.vr.presenter;

import android.content.Context;
import android.os.Bundle;

import com.sky.vr.base.BaseSubscriber;
import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.ResRetailsContract;
import com.sky.vr.data.model.ResRetailsModel;
import com.sky.vr.data.model.ResListModel;
import com.sky.vr.data.source.VideoDataRepository;
import com.sky.vr.data.source.VideoSourceFactory;
import com.sky.vr.download.DownloadManager;
import com.sky.vr.event.ResRetailsEvent;
import com.sky.vr.helper.DownloadHelper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 16-10-22.
 */

public class ResRetailsPresenter extends VRBasePresenter<ResRetailsEvent>
        implements ResRetailsContract.Presenter, DownloadHelper.ServiceListener {

    private int mType;
    private ResListModel.Resource mResource;
    private ResRetailsContract.View mView;
    private VideoDataRepository mRepository;

    private ResRetailsModel mModel;
    private DownloadHelper mDownloadHelper;

    public ResRetailsPresenter(Context context, Bundle args, ResRetailsContract.View view) {
        super(context);
        mType = args.getInt("type");
        mResource = (ResListModel.Resource) args.getSerializable("resource");
        mView = view;
        mRepository = new VideoDataRepository(new VideoSourceFactory(context));
        mDownloadHelper = new DownloadHelper(context, this);
    }

    @Override
    public void loadResRetails() {

        mView.showLoading();

        mRepository.getResRetails(mResource.getUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ResRetailsModel>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        mView.cancelLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.cancelLoading();
                        mView.showMessage("加载详情信息失败");
                    }

                    @Override
                    public void onNext(ResRetailsModel model) {
                        super.onNext(model);
                        // 设置详情信息
                        mModel = model;
                        mView.setResRetails(model);
                    }
                });
    }

    @Override
    public void playResource() {

        System.out.println(">>>>>>>>>>>>>>>>> " + mModel.getDownloadUrl());
    }

    @Override
    public void resume() {
        super.resume();

        // 绑定服务
        mDownloadHelper.bindDownloadService();
    }

    @Override
    public void pause() {
        super.pause();

        // 解绑服务
        mDownloadHelper.bindDownloadService();
    }

    @Override
    public void onServiceConnected(DownloadManager downloadManager) {


    }

    @Override
    public void onServiceDisconnected(DownloadManager downloadManager) {

    }
}
