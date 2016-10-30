package com.sky.vr.download.core;


import android.os.Process;
import android.text.TextUtils;

import com.sky.vr.download.Constants.HTTP;
import com.sky.vr.download.DownloadException;
import com.sky.vr.download.architecture.DownloadStatus;
import com.sky.vr.download.architecture.DownloadTask;
import com.sky.vr.download.entity.DownloadEntity;
import com.sky.vr.download.entity.ThreadEntity;
import com.sky.vr.download.util.IOCloseUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Aspsine on 2015/7/27.
 */
public abstract class DownloadTaskImpl implements DownloadTask {

    private String mTag;

    private final DownloadEntity mDownloadEntity;
    private final ThreadEntity mThreadEntity;
    private final OnDownloadListener mOnDownloadListener;

    private volatile int mStatus;

    private volatile int mCommend = 0;

    public DownloadTaskImpl(DownloadEntity downloadEntity, ThreadEntity threadEntity, OnDownloadListener listener) {
        this.mDownloadEntity = downloadEntity;
        this.mThreadEntity = threadEntity;
        this.mOnDownloadListener = listener;

        this.mTag = getTag();
        if (TextUtils.isEmpty(mTag)) {
            mTag = this.getClass().getSimpleName();
        }
    }

    @Override
    public void cancel() {
        mCommend = DownloadStatus.STATUS_CANCELED;
    }

    @Override
    public void pause() {
        mCommend = DownloadStatus.STATUS_PAUSED;
    }

    @Override
    public boolean isDownloading() {
        return mStatus == DownloadStatus.STATUS_PROGRESS;
    }

    @Override
    public boolean isComplete() {
        return mStatus == DownloadStatus.STATUS_COMPLETED;
    }

    @Override
    public boolean isPaused() {
        return mStatus == DownloadStatus.STATUS_PAUSED;
    }

    @Override
    public boolean isCanceled() {
        return mStatus == DownloadStatus.STATUS_CANCELED;
    }

    @Override
    public boolean isFailed() {
        return mStatus == DownloadStatus.STATUS_FAILED;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        insertIntoDB(mThreadEntity);
        try {
            mStatus = DownloadStatus.STATUS_PROGRESS;
            executeDownload();
            synchronized (mOnDownloadListener) {
                mStatus = DownloadStatus.STATUS_COMPLETED;
                mOnDownloadListener.onDownloadCompleted();
            }
        } catch (DownloadException e) {
            handleDownloadException(e);
        }
    }

    private void handleDownloadException(DownloadException e) {
        switch (e.getErrorCode()) {
            case DownloadStatus.STATUS_FAILED:
                synchronized (mOnDownloadListener) {
                    mStatus = DownloadStatus.STATUS_FAILED;
                    mOnDownloadListener.onDownloadFailed(e);
                }
                break;
            case DownloadStatus.STATUS_PAUSED:
                synchronized (mOnDownloadListener) {
                    mStatus = DownloadStatus.STATUS_PAUSED;
                    mOnDownloadListener.onDownloadPaused();
                }
                break;
            case DownloadStatus.STATUS_CANCELED:
                synchronized (mOnDownloadListener) {
                    mStatus = DownloadStatus.STATUS_CANCELED;
                    mOnDownloadListener.onDownloadCanceled();
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }

    private void executeDownload() throws DownloadException {
        final URL url;
        try {
            url = new URL(mThreadEntity.getUrl());
        } catch (MalformedURLException e) {
            throw new DownloadException(DownloadStatus.STATUS_FAILED, "Bad url.", e);
        }

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setConnectTimeout(HTTP.CONNECT_TIME_OUT);
            httpConnection.setReadTimeout(HTTP.READ_TIME_OUT);
            httpConnection.setRequestMethod(HTTP.GET);
            setHttpHeader(getHttpHeaders(mThreadEntity), httpConnection);
            final int responseCode = httpConnection.getResponseCode();
            if (responseCode == getResponseCode()) {
                transferData(httpConnection);
            } else {
                throw new DownloadException(DownloadStatus.STATUS_FAILED, "UnSupported response code:" + responseCode);
            }
        } catch (ProtocolException e) {
            throw new DownloadException(DownloadStatus.STATUS_FAILED, "Protocol error", e);
        } catch (IOException e) {
            throw new DownloadException(DownloadStatus.STATUS_FAILED, "IO error", e);
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }

    private void setHttpHeader(Map<String, String> headers, URLConnection connection) {
        if (headers != null) {
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
        }
    }

    private void transferData(HttpURLConnection httpConnection) throws DownloadException {
        InputStream inputStream = null;
        RandomAccessFile raf = null;
        try {
            try {
                inputStream = httpConnection.getInputStream();
            } catch (IOException e) {
                throw new DownloadException(DownloadStatus.STATUS_FAILED, "http get inputStream error", e);
            }
            final long offset = mThreadEntity.getStart() + mThreadEntity.getFinished();
            try {
                raf = getFile(new File(mDownloadEntity.getDir()), mDownloadEntity.getTempName(), offset);
            } catch (IOException e) {
                throw new DownloadException(DownloadStatus.STATUS_FAILED, "File error", e);
            }
            transferData(inputStream, raf);
        } finally {
            try {
                IOCloseUtils.close(inputStream);
                IOCloseUtils.close(raf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void transferData(InputStream inputStream, RandomAccessFile raf) throws DownloadException {
        final byte[] buffer = new byte[1024 * 8];
        while (true) {
            checkPausedOrCanceled();
            int len = -1;
            try {
                len = inputStream.read(buffer);
            } catch (IOException e) {
                throw new DownloadException(DownloadStatus.STATUS_FAILED, "Http inputStream read error", e);
            }

            if (len == -1) {
                break;
            }

            try {
                raf.write(buffer, 0, len);
                mThreadEntity.setFinished(mThreadEntity.getFinished() + len);
                synchronized (mOnDownloadListener) {
                    mDownloadEntity.setFinished(mDownloadEntity.getFinished() + len);
                    mOnDownloadListener.onDownloadProgress(mDownloadEntity.getFinished(), mDownloadEntity.getLength());
                }
            } catch (IOException e) {
                throw new DownloadException(DownloadStatus.STATUS_FAILED, "Fail write buffer to file", e);
            }
        }
    }


    private void checkPausedOrCanceled() throws DownloadException {
        if (mCommend == DownloadStatus.STATUS_CANCELED) {
            // cancel
            throw new DownloadException(DownloadStatus.STATUS_CANCELED, "Download canceled!");
        } else if (mCommend == DownloadStatus.STATUS_PAUSED) {
            // pause
            updateDB(mThreadEntity);
            throw new DownloadException(DownloadStatus.STATUS_PAUSED, "Download paused!");
        }
    }


    protected abstract void insertIntoDB(ThreadEntity entity);

    protected abstract int getResponseCode();

    protected abstract void updateDB(ThreadEntity entity);

    protected abstract Map<String, String> getHttpHeaders(ThreadEntity entity);

    protected abstract RandomAccessFile getFile(File dir, String name, long offset) throws IOException;

    protected abstract String getTag();
}