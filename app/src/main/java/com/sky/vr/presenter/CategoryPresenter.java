package com.sky.vr.presenter;

import android.content.Context;

import com.sky.vr.base.VRBasePresenter;
import com.sky.vr.contract.CategoryContract;
import com.sky.vr.data.mojing.Tags;
import com.sky.vr.data.mojing.TagsResource;
import com.sky.vr.data.source.VideoRepository;
import com.sky.vr.data.source.local.VideoLocalDataSource;
import com.sky.vr.data.source.remote.VideoRemoteDataSource;
import com.sky.vr.event.VideoEvent;
import com.sky.vr.mapper.CategoryMapper;
import com.sky.vr.model.CategoryModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 16-9-28.
 */

public class CategoryPresenter extends VRBasePresenter<VideoEvent> implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private VideoRepository mRepository;

    public CategoryPresenter(Context context, CategoryContract.View view) {
        super(context);
        mView = view;
        view.setPresenter(this);
        mRepository = new VideoRepository(
                new VideoLocalDataSource(getContext()), new VideoRemoteDataSource());
    }

    @Override
    public void onMainThreadEvent(VideoEvent event) {

    }

    @Override
    public void loadCategory() {

        mView.showLoading();

        mRepository.getCategory()
                .subscribeOn(Schedulers.io())
                .map(new Func1<Tags, CategoryModel>() {
                    @Override
                    public CategoryModel call(Tags tags) {

                        CategoryMapper mapper = new CategoryMapper();

                        return mapper.transform(tags);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryModel>() {
                    @Override
                    public void onCompleted() {
                        mView.cancelLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage("获取标题列表失败");
                    }

                    @Override
                    public void onNext(CategoryModel categoryModel) {
                        // 视频
                        CategoryModel.Category category = categoryModel.getCategories().get(1);
                        mView.setCategory(category.getResId(), category.getSubCategories());
                    }
                });
    }
}
