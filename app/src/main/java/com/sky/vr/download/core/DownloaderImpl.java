package com.sky.vr.download.core;

import com.sky.vr.download.DownloadConfiguration;
import com.sky.vr.download.DownloadDB;
import com.sky.vr.download.DownloadException;
import com.sky.vr.download.architecture.ConnectTask;
import com.sky.vr.download.architecture.DownloadResponse;
import com.sky.vr.download.architecture.DownloadStatus;
import com.sky.vr.download.architecture.DownloadTask;
import com.sky.vr.download.architecture.Downloader;
import com.sky.vr.download.entity.DownloadEntity;
import com.sky.vr.download.entity.ThreadEntity;
import com.sky.vr.download.entity.ThreadEntityDao;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Aspsine on 2015/10/28.
 */
public class DownloaderImpl implements Downloader, ConnectTask.OnConnectListener, DownloadTask.OnDownloadListener {

    private DownloadResponse mResponse;

    private Executor mExecutor;

    private DownloadDB mDownloadDB;

    private DownloadConfiguration mConfig;

    private OnDownloaderDestroyedListener mListener;

    private DownloadEntity mDownloadEntity;

    private ConnectTask mConnectTask;

    private List<DownloadTask> mDownloadTasks;

    public DownloaderImpl(DownloadEntity downloadEntity, DownloadResponse response, Executor executor, DownloadDB downloadDB, DownloadConfiguration config, OnDownloaderDestroyedListener listener) {
        mDownloadEntity = downloadEntity;
        mResponse = response;
        mExecutor = executor;
        mDownloadDB = downloadDB;
        mConfig = config;
        mListener = listener;

        init();
    }

    private void init() {
        mDownloadTasks = new LinkedList<>();
    }

    private long getTid() {
        return mDownloadEntity.getId();
    }

    private String getUrl() {
        return mDownloadEntity.getUrl();
    }

    /**
     * 设置下载状态
     * @param status
     */
    private void setStatus(int status) {
        mDownloadEntity.setState(status);
    }

    /**
     * 更新信息到数据库
     */
    private void updateToDB() {
        // 更新状态
        mDownloadDB.getDownloadEntityDao().update(mDownloadEntity);
    }

    /**
     * 获取下载的状态信息
     * @return
     */
    private int getStatus() {
        return mDownloadEntity.getState();
    }

    @Override
    public boolean isRunning() {

        int status = getStatus();

        return status == DownloadStatus.STATUS_STARTED
                || status == DownloadStatus.STATUS_CONNECTING
                || status == DownloadStatus.STATUS_CONNECTED
                || status == DownloadStatus.STATUS_PROGRESS;
    }

    @Override
    public void start() {
        setStatus(DownloadStatus.STATUS_STARTED);
        updateToDB();
        mResponse.onStarted();
        connect();
    }

    @Override
    public void pause() {
        if (mConnectTask != null) {
            mConnectTask.cancel();
        }
        for (DownloadTask task : mDownloadTasks) {
            task.pause();
        }
    }

    @Override
    public void cancel() {
        if (mConnectTask != null) {
            mConnectTask.cancel();
        }
        for (DownloadTask task : mDownloadTasks) {
            task.cancel();
        }
    }

    @Override
    public void onDestroy() {
        // trigger the onDestroy callback tell download manager
        mListener.onDestroyed(getTid(), this);
    }

    @Override
    public void onConnecting() {
        setStatus(DownloadStatus.STATUS_CONNECTING);
        mResponse.onConnecting();
    }

    @Override
    public void onConnected(long time, long length, boolean isAcceptRanges) {
        setStatus(DownloadStatus.STATUS_CONNECTED);
        mResponse.onConnected(time, length, isAcceptRanges);

        mDownloadEntity.setAcceptRanges(isAcceptRanges);
        mDownloadEntity.setLength(length);
        download(length, isAcceptRanges);
    }

    @Override
    public void onConnectFailed(DownloadException de) {
        setStatus(DownloadStatus.STATUS_FAILED);
        updateToDB();
        mResponse.onConnectFailed(de);
        onDestroy();
    }

    @Override
    public void onConnectCanceled() {
        setStatus(DownloadStatus.STATUS_CANCELED);
        mResponse.onConnectCanceled();
        onDestroy();
    }

    @Override
    public void onDownloadConnecting() {
    }

    @Override
    public void onDownloadProgress(long finished, long length) {
        // calculate percent
        final int percent = (int) (finished * 100 / length);

        // 更新信息
        setStatus(DownloadStatus.STATUS_PROGRESS);
        mDownloadEntity.setFinished(finished);
        mDownloadEntity.setLength(length);
        mDownloadEntity.setProgress(percent);

        mResponse.onDownloadProgress(finished, length, percent);
    }

    @Override
    public void onDownloadCompleted() {
        if (isAllComplete()) {
            deleteFromDB();
            setStatus(DownloadStatus.STATUS_COMPLETED);
            updateToDB();
            mResponse.onDownloadCompleted();
            onDestroy();
        }
    }

    @Override
    public void onDownloadPaused() {
        if (isAllPaused()) {
            setStatus(DownloadStatus.STATUS_PAUSED);
            updateToDB();
            mResponse.onDownloadPaused();
            onDestroy();
        }
    }

    @Override
    public void onDownloadCanceled() {
        if (isAllCanceled()) {
            deleteFromDB();
            setStatus(DownloadStatus.STATUS_CANCELED);
            updateToDB();
            mResponse.onDownloadCanceled();
            onDestroy();
        }
    }

    @Override
    public void onDownloadFailed(DownloadException de) {
        if (isAllFailed()) {
            setStatus(DownloadStatus.STATUS_FAILED);
            updateToDB();
            mResponse.onDownloadFailed(de);
            onDestroy();
        }
    }

    private void connect() {
        mConnectTask = new ConnectTaskImpl(getUrl(), this);
        mExecutor.execute(mConnectTask);
    }

    private void download(long length, boolean acceptRanges) {
        initDownloadTasks(length, acceptRanges);
        // start tasks
        for (DownloadTask downloadTask : mDownloadTasks) {
            mExecutor.execute(downloadTask);
        }
    }

    //TODO
    private void initDownloadTasks(long length, boolean acceptRanges) {
        mDownloadTasks.clear();
        if (acceptRanges) {
            List<ThreadEntity> threadInfos = getMultiThreadInfos(length);
            // init finished
            long finished = 0;
            for (ThreadEntity threadInfo : threadInfos) {
                finished += threadInfo.getFinished();
            }
            mDownloadEntity.setFinished(finished);
            for (ThreadEntity info : threadInfos) {
                mDownloadTasks.add(new MultiDownloadTask(mDownloadEntity, info, mDownloadDB, this));
            }
        } else {
            ThreadEntity info = getSingleThreadInfo();
            mDownloadTasks.add(new SingleDownloadTask(mDownloadEntity, info, this));
        }
    }

    //TODO
    private List<ThreadEntity> getMultiThreadInfos(long length) {
        // init threadInfo from db
        final List<ThreadEntity> threadInfos = mDownloadDB.getThreadEntityDao()
                .queryBuilder().where(ThreadEntityDao.Properties.Tid.eq(getTid())).build().list();
        if (threadInfos.isEmpty()) {
            final int threadNum = mConfig.getThreadNum();
            for (int i = 0; i < threadNum; i++) {
                // calculate average
                final long average = length / threadNum;
                final long start = average * i;
                final long end;
                if (i == threadNum - 1) {
                    end = length;
                } else {
                    end = start + average - 1;
                }
                ThreadEntity threadInfo = newThreadInfo(getTid(), getUrl(), start, end, 0);
                threadInfos.add(threadInfo);
            }
        }
        return threadInfos;
    }

    //TODO
    private ThreadEntity getSingleThreadInfo() {
        return newThreadInfo(getTid(), getUrl(), 0, 0, 0);
    }

    private ThreadEntity newThreadInfo(long tid, String url, long start, long end, long finished) {

        ThreadEntity newThreadInfo = new ThreadEntity();

        newThreadInfo.setTid(tid);
        newThreadInfo.setUrl(url);
        newThreadInfo.setStart(start);
        newThreadInfo.setEnd(end);
        newThreadInfo.setFinished(finished);

        return newThreadInfo;
    }

    private boolean isAllComplete() {
        boolean allFinished = true;
        for (DownloadTask task : mDownloadTasks) {
            if (!task.isComplete()) {
                allFinished = false;
                break;
            }
        }
        return allFinished;
    }

    private boolean isAllFailed() {
        boolean allFailed = true;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                allFailed = false;
                break;
            }
        }
        return allFailed;
    }

    private boolean isAllPaused() {
        boolean allPaused = true;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                allPaused = false;
                break;
            }
        }
        return allPaused;
    }

    private boolean isAllCanceled() {
        boolean allCanceled = true;
        for (DownloadTask task : mDownloadTasks) {
            if (task.isDownloading()) {
                allCanceled = false;
                break;
            }
        }
        return allCanceled;
    }

    private void deleteFromDB() {
        // 删除所有下载信息
        List<ThreadEntity> threadInfos = mDownloadDB.getThreadEntityDao()
                .queryBuilder().where(ThreadEntityDao.Properties.Tid.eq(getTid())).build().list();
        mDownloadDB.getThreadEntityDao().deleteInTx(threadInfos);
    }
}
