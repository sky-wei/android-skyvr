package com.sky.vr.download;


import com.sky.android.common.utils.Alog;
import com.sky.vr.download.entity.DownloadEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by starrysky on 16-5-16.
 */
public class DownloadMonitor implements CallBack {

    private static final String TAG = "DownloadMonitor";

    private DownloadManagerImpl mDownloadManager;
    private List<DownloadListener> mDownloadListeners;

    public DownloadMonitor(DownloadManagerImpl downloadManager) {
        mDownloadManager = downloadManager;
        mDownloadListeners = new ArrayList<>();
    }

    /**
     * 注册下载监听
     * @param downloadListener
     */
    public void registerListener(DownloadListener downloadListener) {

        if (downloadListener == null) return ;

        // 添加监听器
        mDownloadListeners.add(downloadListener);

        Alog.d(TAG, "registerListener " + mDownloadListeners.size() + ", " + downloadListener);
    }

    public void unregisterListener(DownloadListener downloadListener) {

        if (downloadListener == null) return ;

        // 移除监听器
        mDownloadListeners.remove(downloadListener);

        Alog.d(TAG, "unregisterListener " + mDownloadListeners.size() + ", " + downloadListener);
    }

    @Override
    public void onStarted(long id) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onStarted(id);
        }
    }

    @Override
    public void onConnecting(long id) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onConnecting(id);
        }
    }

    @Override
    public void onConnected(long id, long total, boolean isRangeSupport) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onConnected(id, total, isRangeSupport);
        }
    }

    @Override
    public void onProgress(long id, long finished, long total, int progress) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onProgress(id, finished, total, progress);
        }
    }

    @Override
    public void onCompleted(long id) {

        DownloadEntity downloadInfo = mDownloadManager.getDownloadEntity(id);

        if (downloadInfo != null) {
            // 移动文件
            File tempFile = new File(downloadInfo.getDir(), downloadInfo.getTempName());
            tempFile.renameTo(new File(downloadInfo.getDir(), downloadInfo.getName()));
        }

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onCompleted(id);
        }
    }

    @Override
    public void onDownloadPaused(long id) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onDownloadPaused(id);
        }
    }

    @Override
    public void onDownloadCanceled(long id) {

        // 删除任务信息
        mDownloadManager.delete(id);

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onDownloadCanceled(id);
        }
    }

    @Override
    public void onFailed(long id, DownloadException e) {

        if (mDownloadListeners.isEmpty()) return ;

        for (DownloadListener downloadListener : mDownloadListeners) {
            // 通知所有的
            downloadListener.onFailed(id, e);
        }
    }
}
