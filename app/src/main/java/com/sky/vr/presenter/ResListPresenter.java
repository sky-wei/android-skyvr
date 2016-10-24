package com.sky.vr.presenter;

import android.content.Context;
import android.os.Bundle;

import com.sky.vr.base.BaseSubscriber;
import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.ResListContract;
import com.sky.vr.data.source.VideoDataRepository;
import com.sky.vr.data.source.VideoSourceFactory;
import com.sky.vr.event.ResListEvent;
import com.sky.vr.data.model.ResListModel;
import com.sky.vr.util.PageHelper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 16-9-28.
 */

public class ResListPresenter extends VRBasePresenter<ResListEvent> implements ResListContract.Presenter {

    private int mType;
    private int mResId;
    private int mTag;
    private ResListContract.View mView;
    private VideoDataRepository mRepository;
    private PageHelper<ResListModel.Resource> mPageHelper;

    public ResListPresenter(Context context, Bundle args, ResListContract.View view) {
        super(context);
        mType = args.getInt("type");
        mResId = args.getInt("resId");
        mTag = args.getInt("tag");
        mView = view;
        mRepository = new VideoDataRepository(new VideoSourceFactory(context));
        mPageHelper = new PageHelper<>();
    }

    @Override
    public void onMainThreadEvent(ResListEvent event) {

    }

    @Override
    public void loadTagsResource() {
        loadTagsResource(0, false);
    }

    @Override
    public void loadMoreTagsResource() {

        if (!mPageHelper.isNextPage()) {
            mView.cancelLoading();
            return ;
        }

        // 加载更多数据
        loadTagsResource(mPageHelper.getCurPage() + 1, true);
    }

    @Override
    public void onItemEvent(int position) {
        mView.seeDetails(mType, mPageHelper.getDataItem(position));
    }

    private void loadTagsResource(int curPage, final boolean loadMore) {

        mView.showLoading();

        mRepository.getResourceList(
                mResId, mTag, curPage * PageHelper.PAGE_SIZE, PageHelper.PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ResListModel>() {
                    @Override
                    public void onCompleted() {
                        mView.cancelLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.cancelLoading();
                        mView.showMessage("加载数据失败");
                    }

                    @Override
                    public void onNext(ResListModel model) {

                        if (model == null) {
                            mView.showMessage("没有加载到服务器数据");
                            return ;
                        }

                        if (loadMore) {
                            // 追加数据
                            mPageHelper.appendData(model.getResources());
                        } else {
                            // 设置数据
                            mPageHelper.setData(model.getTotal(), model.getResources());
                        }

                        mView.setTagsResource(mPageHelper.getData());
                    }
                });
    }
}
