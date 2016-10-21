package com.sky.vr.presenter;

import android.content.Context;
import android.os.Bundle;

import com.sky.vr.base.BaseSubscriber;
import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.CategoryContract;
import com.sky.vr.data.source.VideoDataRepository;
import com.sky.vr.data.source.VideoSourceFactory;
import com.sky.vr.event.VideoEvent;
import com.sky.vr.data.model.CategoryModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 16-9-28.
 */

public class CategoryPresenter extends VRBasePresenter<VideoEvent> implements CategoryContract.Presenter {

    public static final int TYPE_VIDEO = 0x0001;
    public static final int TYPE_PICTURE = 0x0002;

    private int mType;
    private CategoryContract.View mView;
    private VideoDataRepository mRepository;

    public CategoryPresenter(Context context, Bundle args, CategoryContract.View view) {
        super(context);
        // 分类类型
        mType = args.getInt("type", TYPE_VIDEO);;
        mView = view;
        mRepository = new VideoDataRepository(new VideoSourceFactory(context));
    }

    @Override
    public void onMainThreadEvent(VideoEvent event) {

    }

    @Override
    public void loadCategory() {

        mView.showLoading();

        mRepository.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CategoryModel>() {
                    @Override
                    public void onCompleted() {
                        mView.cancelLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.cancelLoading();
                        mView.showMessage("获取标题列表失败");
                    }

                    @Override
                    public void onNext(CategoryModel categoryModel) {

                        // 视频|图片
                        int index = TYPE_VIDEO == mType ? 1 : 0;
                        CategoryModel.Category category = categoryModel.getCategories().get(index);
                        mView.setCategory(mType, category.getResId(), category.getSubCategories());
                    }
                });
    }
}
