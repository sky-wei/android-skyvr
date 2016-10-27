package com.sky.vr.data.cache.impl;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jakewharton.disklrucache.DiskLruCache;
import com.sky.android.common.utils.Alog;
import com.sky.android.common.utils.FileUtils;
import com.sky.android.common.utils.MD5Utils;
import com.sky.vr.data.cache.CacheManager;
import com.sky.vr.util.AppUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by sky on 16-10-27.
 */

public class CacheManagerImpl implements CacheManager {

    private static final String TAG = CacheManagerImpl.class.getSimpleName();

    private static final int MAX_SIZE = 1024 * 1024 * 20;

    private Context mContext;
    private DiskLruCache mDiskLruCache;
    private Gson mGson;

    public CacheManagerImpl(Context context) {
        mContext = context;
        mGson = new Gson();
        initCache();
    }

    private void initCache() {

        String packageName = mContext.getPackageName();
        int version = AppUtil.getAppVersionCode(mContext, packageName);

        File cacheDir = new File(mContext.getCacheDir(), "net_cache");
        if (!cacheDir.exists()) FileUtils.createDir(cacheDir);

        try {
            mDiskLruCache = DiskLruCache.open(cacheDir, version, 1, MAX_SIZE);
        } catch (IOException e) {
            Alog.e(TAG, "打开缓存目录异常", e);
            mDiskLruCache = null;
        }
    }

    @Override
    public synchronized <T> T get(String key, Class<T> tClass) {

        if (TextUtils.isEmpty(key) || tClass == null) {
            return null;
        }

        try {
            // 获取缓存信息
            DiskLruCache.Snapshot snapshot = get(key);

            if (snapshot != null) {
                // 返回相应的信息
                return mGson.fromJson(snapshot.getString(0), tClass);
            }
        } catch (Exception e) {
            Alog.e(TAG, "获取信息失败", e);
        }
        return null;
    }

    @Override
    public synchronized <T> boolean put(String key, T value) {

        if (TextUtils.isEmpty(key) || value == null) return false;

        DiskLruCache.Editor editor = null;

        try {
            // 获取编辑器
            editor = edit(key);

            if (editor != null) {
                // 保存数据
                editor.set(0, mGson.toJson(value));
                editor.commit();
                return true;
            }
        } catch (Exception e) {
            abortQuietly(editor);
            Alog.e(TAG, "保存分类信息出错！", e);
        } finally {
            flushQuietly();
        }
        return false;
    }

    public synchronized boolean remove(String key) {

        if (TextUtils.isEmpty(key) || !verifyCache()) return false;

        try {
            // 删除数据
            mDiskLruCache.remove(key);
            return true;
        } catch (IOException e) {
            Alog.e(TAG, "移除数据失败", e);
        }
        return false;
    }

    @Override
    public synchronized void close() {

        if (!verifyCache()) return ;

        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            Alog.e(TAG, "关闭缓存失败", e);
        }
    }

    @Override
    public String buildKey(String value) {
        return MD5Utils.md5sum(value);
    }

    private boolean verifyCache() {
        return mDiskLruCache == null ? false : true;
    }

    private DiskLruCache.Snapshot get(String key) throws IOException {
        return verifyCache() ? mDiskLruCache.get(key) : null;
    }

    private DiskLruCache.Editor edit(String key) throws IOException {
        return verifyCache() ? mDiskLruCache.edit(key) : null;
    }

    private void abort(DiskLruCache.Editor editor) throws IOException {
        if (editor != null) editor.abort();
    }

    private void abortQuietly(DiskLruCache.Editor editor) {
        try {
            abort(editor);
        } catch (IOException e) {
            Alog.e(TAG, "abortQuietly", e);
        }
    }

    private void flush() throws IOException {
        if (verifyCache()) mDiskLruCache.flush();
    }

    private void flushQuietly() {
        try {
            flush();
        } catch (IOException e) {
            Alog.e(TAG, "flushQuietly", e);
        }
    }
}
