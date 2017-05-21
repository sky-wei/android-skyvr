package com.sky.vr.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.sky.android.common.utils.Alog;
import com.sky.vr.download.DownloadFile;
import com.sky.vr.download.DownloadListener;
import com.sky.vr.download.DownloadManager;
import com.sky.vr.download.DownloadService;
import com.sky.vr.download.entity.DownloadEntity;

import java.util.List;

/**
 * Created by sky on 17-2-18.
 */
public class DownloadHelper implements DownloadManager {

    private static final String TAG = "DownloadHelper";

    public static final int SERVICE_CONNECTED = 0x101;
    public static final int SERVICE_DISCONNECTED = 0x102;

    private Context mContext;

    private DownloadManager mDownloadManager;
    private ServiceConnection mServiceConnection;

    private ServiceListener mServiceListener;
    private DownloadListener mDownloadListener;

    public DownloadHelper(Context context, ServiceListener serviceListener, DownloadListener downloadListener) {
        mContext = context;
        mServiceListener = serviceListener;
        mDownloadListener = downloadListener;
    }

    /**
     * 绑定服务
     */
    public void bindDownloadService() {

        if (mServiceConnection != null) return ;

        try {
            // 绑定服务
            mServiceConnection = new DownloadServiceConnection();
            Intent intent = new Intent(mContext, DownloadService.class);
            mContext.bindService(intent,
                    mServiceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Alog.e(TAG, "绑定服务异常", e);
        }
    }

    /**
     * 解绑服务
     */
    public void unbindDownloadService() {

        if (mServiceConnection == null) return ;

        try {
            // 移除监听
            mDownloadManager.unregisterDownloadListener(mDownloadListener);

            // 解绑服务
            mContext.unbindService(mServiceConnection);
            mServiceConnection = null;
            mDownloadManager = null;
        } catch (Exception e) {
            Alog.e(TAG, "解绑服务异常", e);
        }
    }

    public boolean isServiceContent() {
        return mDownloadManager != null;
    }

    @Override
    public long add(DownloadFile downloadFile) {
        return isServiceContent() ? mDownloadManager.add(downloadFile) : -1;
    }

    @Override
    public void start(long id) {
        if (isServiceContent()) mDownloadManager.start(id);
    }

    @Override
    public boolean isDownloading(long id) {
        return isServiceContent() ? mDownloadManager.isDownloading(id) : false;
    }

    @Override
    public void pause(long id) {
        if (isServiceContent()) mDownloadManager.pause(id);
    }

    @Override
    public void cancel(long id) {
        if (isServiceContent()) mDownloadManager.cancel(id);
    }

    @Override
    public void pauseAll() {
        if (isServiceContent()) mDownloadManager.pauseAll();
    }

    @Override
    public void cancelAll() {
        if (isServiceContent()) mDownloadManager.cancelAll();
    }

    @Override
    public void delete(long id) {
        if (isServiceContent()) mDownloadManager.delete(id);
    }

    @Override
    public DownloadEntity getDownloadEntity(long id) {
        return isServiceContent() ? mDownloadManager.getDownloadEntity(id) : null;
    }

    @Override
    public List<DownloadEntity> getDownloadEntities(long... ids) {
        return isServiceContent() ? mDownloadManager.getDownloadEntities(ids) : null;
    }

    @Override
    public DownloadEntity getDownloadProgress(long id) {
        return isServiceContent() ? mDownloadManager.getDownloadProgress(id) : null;
    }

    @Override
    public List<DownloadEntity> getCompletes() {
        return isServiceContent() ? mDownloadManager.getCompletes() : null;
    }

    @Override
    public List<DownloadEntity> getUnCompletes() {
        return isServiceContent() ? mDownloadManager.getUnCompletes() : null;
    }

    @Override
    public void registerDownloadListener(DownloadListener listener) {
        if (isServiceContent()) mDownloadManager.registerDownloadListener(listener);
    }

    @Override
    public void unregisterDownloadListener(DownloadListener listener) {
        if (isServiceContent()) mDownloadManager.unregisterDownloadListener(listener);
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

    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            onHandleMessage(msg);
        }
    };

    /**
     * 下载连接处理类
     */
    private final class DownloadServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            DownloadService.LocalBinder localBinder = (DownloadService.LocalBinder) service;
            mDownloadManager = localBinder.getDownloadManager();

            // 注册监听
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

    public interface ServiceListener {

        void onServiceConnected(DownloadHelper serviceHelper);

        void onServiceDisconnected(DownloadHelper serviceHelper);
    }
}
