package com.sky.vr.data.cache.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.disklrucache.DiskLruCache;
import com.sky.android.common.utils.Alog;
import com.sky.android.common.utils.FileUtils;
import com.sky.android.common.utils.MD5Utils;
import com.sky.vr.data.cache.VideoCache;
import com.sky.vr.data.model.CategoryModel;
import com.sky.vr.util.AppUtil;

import java.io.File;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by sky on 16-10-21.
 */

public class VideoCacheImpl implements VideoCache {

    private static final String TAG = VideoCacheImpl.class.getSimpleName();

    private static final int MAX_SIZE = 1024 * 1024 * 50;

    private String mCategoryKey;
    private DiskLruCache mDiskLruCache;

    private static VideoCache videoCache;

    public static VideoCache getVideoCache(Context context) {

        if (videoCache == null) {
            videoCache = new VideoCacheImpl(context);
        }

        return videoCache;
    }

    private VideoCacheImpl(Context context) {

        int version = AppUtil.getAppVersionCode(context, context.getPackageName());

        File cacheDir = new File(context.getCacheDir(), "net_cache");
        if (!cacheDir.exists()) FileUtils.createDir(cacheDir);

        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, version, 1, MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
            mDiskLruCache = null;
        }

        // 生成Key
        mCategoryKey = MD5Utils.md5sum(VideoCacheImpl.class.getName() + ":getCategory()");
    }

    @Override
    public Observable<CategoryModel> getCategory() {

        if (mDiskLruCache == null) return Observable.just(null);

        return Observable.create(new Observable.OnSubscribe<CategoryModel>() {
            @Override
            public void call(Subscriber<? super CategoryModel> subscriber) {

                try {
                    // 获取分类缓存
                    subscriber.onNext(getCategoryCache());
                } catch (IOException e) {
                    subscriber.onError(e);
                    return ;
                }
                // 完成
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public void saveCategory(CategoryModel model) {

        DiskLruCache.Editor editor = null;

        try {
            editor = mDiskLruCache.edit(mCategoryKey);

            // 保存数据
            setEditorValue(editor, model);

            // 提交
            editorCommit(editor);
        } catch (Exception e) {
            editorAbort(editor);
            Alog.e(TAG, "保存分类信息出错！", e);
        } finally {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private CategoryModel getCategoryCache() throws IOException {

        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(mCategoryKey);

        if (snapshot == null) return null;

        return new Gson().fromJson(snapshot.getString(0), CategoryModel.class);
    }

    private void setEditorValue(DiskLruCache.Editor editor, CategoryModel model) throws IOException {

        if (editor == null) return ;

        editor.set(0, new Gson().toJson(model));
    }

    private void editorAbort(DiskLruCache.Editor editor) {

        if (editor == null) return ;

        try {
            editor.abort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editorCommit(DiskLruCache.Editor editor) {

        if (editor == null) return ;

        try {
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
