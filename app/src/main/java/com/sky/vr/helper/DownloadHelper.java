package com.sky.vr.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;

import com.sky.vr.download.DownloadException;
import com.sky.vr.download.DownloadFile;
import com.sky.vr.download.DownloadListener;
import com.sky.vr.download.DownloadManager;
import com.sky.vr.download.DownloadService;
import com.sky.vr.download.entity.DownloadEntity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by sky on 16-10-30.
 */

public class DownloadHelper implements DownloadManager {

    public static final int SERVICE_CONNECTED = 0x101;
    public static final int SERVICE_DISCONNECTED = 0x102;

    private Context mContext;
    private ServiceListener mServiceListener;
    private DownloadListener mDownloadListener;
    private List<DownloadListener> mDownloadListeners;

    protected DownloadManager mDownloadManager;
    private ServiceConnection mServiceConnection;

    public DownloadHelper(@NonNull Context context, @NonNull ServiceListener serviceListener) {
        mContext = context;
        mServiceListener = serviceListener;
        mDownloadListener = new DownloadCallback();
        mDownloadListeners = new ArrayList<>();
    }

    private boolean verify() {
        return mDownloadManager != null ? true : false;
    }

    @Override
    public long add(DownloadFile downloadFile) {
        return verify() ? mDownloadManager.add(downloadFile) : 0L;
    }

    @Override
    public void start(long id) {
        if (verify()) mDownloadManager.start(id);
    }

    @Override
    public boolean isDownloading(long id) {
        return verify() ? mDownloadManager.isDownloading(id) : false;
    }

    @Override
    public void pause(long id) {
        if (verify()) mDownloadManager.pause(id);
    }

    @Override
    public void cancel(long id) {
        if (verify()) mDownloadManager.cancel(id);
    }

    @Override
    public void pauseAll() {
        if (verify()) mDownloadManager.pauseAll();
    }

    @Override
    public void cancelAll() {
        if (verify()) mDownloadManager.cancelAll();
    }

    @Override
    public void delete(long id) {
        if (verify()) mDownloadManager.delete(id);
    }

    @Override
    public DownloadEntity getDownloadEntity(long id) {
        return verify() ? mDownloadManager.getDownloadEntity(id) : null;
    }

    @Override
    public List<DownloadEntity> getDownloadEntities(long... ids) {
        return verify() ? mDownloadManager.getDownloadEntities(ids) : null;
    }

    @Override
    public DownloadEntity getDownloadProgress(long id) {
        return verify() ? mDownloadManager.getDownloadProgress(id) : null;
    }

    @Override
    public List<DownloadEntity> getCompletes() {
        return verify() ? mDownloadManager.getCompletes() : null;
    }

    @Override
    public List<DownloadEntity> getUncompletes() {
        return verify() ? mDownloadManager.getUncompletes() : null;
    }

    @Override
    public void registerDownloadListener(DownloadListener listener) {
        if (listener != null) mDownloadListeners.add(listener);
    }

    @Override
    public void unregisterDownloadListener(DownloadListener listener) {
        if (listener != null) mDownloadListeners.remove(listener);
    }

    /**
     * 绑定服务
     */
    public void bindDownloadService() {

        if (mServiceConnection != null) return ;

        // 绑定服务
        mServiceConnection = new DownloadServiceConnection();
        Intent intent = new Intent(mContext, DownloadService.class);

        mContext.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     */
    public void unbindDownloadService() {

        if (mServiceConnection == null) return ;

        mDownloadManager.unregisterDownloadListener(mDownloadListener);
        mDownloadListener = null;

        // 解绑服务
        mContext.unbindService(mServiceConnection);
        mServiceConnection = null;
        mDownloadManager = null;
    }

    void onHandleMessage(Message msg) {

        if (SERVICE_CONNECTED == msg.what) {
            // 连接成功
            mServiceListener.onServiceConnected(this);
        } else if (SERVICE_DISCONNECTED == msg.what) {
            // 连接失败
            mServiceListener.onServiceDisconnected(this);
        }
    }

    /**
     * 下载连接处理类
     */
    private final class DownloadServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            DownloadService.LocalBinder localBinder = (DownloadService.LocalBinder) service;
            mDownloadManager = localBinder.getDownloadManager();
            mDownloadManager.registerDownloadListener(mDownloadListener);

            // 发送连接成功消息
            mHandler.sendEmptyMessage(SERVICE_CONNECTED);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            mDownloadManager = null;

            // 发送连接失败的消息
            mHandler.sendEmptyMessage(SERVICE_DISCONNECTED);
        }
    }


    protected final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            onHandleMessage(msg);
        }
    };


    private final class DownloadCallback implements DownloadListener {

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

    public interface ServiceListener {

        void onServiceConnected(DownloadManager downloadManager);

        void onServiceDisconnected(DownloadManager downloadManager);
    }
}
