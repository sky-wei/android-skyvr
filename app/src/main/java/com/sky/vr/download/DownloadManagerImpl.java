package com.sky.vr.download;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sky.android.common.utils.Alog;
import com.sky.vr.download.architecture.DownloadResponse;
import com.sky.vr.download.architecture.DownloadStatus;
import com.sky.vr.download.architecture.DownloadStatusDelivery;
import com.sky.vr.download.architecture.Downloader;
import com.sky.vr.download.core.DownloadResponseImpl;
import com.sky.vr.download.core.DownloadStatusDeliveryImpl;
import com.sky.vr.download.core.DownloaderImpl;
import com.sky.vr.download.entity.DownloadEntity;
import com.sky.vr.download.entity.DownloadEntityDao;
import com.sky.vr.download.entity.ThreadEntity;
import com.sky.vr.download.entity.ThreadEntityDao;
import com.sky.vr.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by starrysky on 16-5-14.
 */
public class DownloadManagerImpl implements Downloader.OnDownloaderDestroyedListener, DownloadManager {

    private static final String TAG = "DownloadManager";

    private Context mContext;

    private Map<Long, Downloader> mDownloaderMap;
    private Map<Long, DownloadEntity> mDownloadInfoMap;
    private DownloadConfiguration mConfig;
    private ExecutorService mExecutorService;
    private DownloadStatusDelivery mDelivery;
    private DownloadDB mDBDbManager;

    private DownloadMonitor mDownloadMonitor;


    public DownloadManagerImpl(Context context) {
        mContext = context;
    }

    public void init(@NonNull DownloadConfiguration config) {

        if (config.getThreadNum() > config.getMaxThreadNum()) {
            throw new IllegalArgumentException("thread num must < max thread num");
        }

        mConfig = config;
        mDownloaderMap = new LinkedHashMap<>();
        mDownloadInfoMap = new HashMap<>();
        mExecutorService = Executors.newFixedThreadPool(mConfig.getMaxThreadNum());
        mDelivery = new DownloadStatusDeliveryImpl(new Handler(Looper.getMainLooper()));
        mDBDbManager = DownloadDB.getInstance(mContext);
        mDownloadMonitor = new DownloadMonitor(this);

        // 加载所有下载信息
        loadDownloadInfo();
    }

    public void release() {

        mDownloaderMap.clear();
        mDownloadInfoMap.clear();
    }

    private void loadDownloadInfo() {

        Alog.d(TAG, "loadDownloadInfo");

        mDownloadInfoMap.clear();

        // 加载所有下载信息
        List<DownloadEntity> downloadInfos = mDBDbManager.getDownloadEntityDao().loadAll();

        if (downloadInfos == null || downloadInfos.isEmpty()) return ;

        for (int i = 0; i< downloadInfos.size(); i++) {

            DownloadEntity downloadInfo = downloadInfos.get(i);
            mDownloadInfoMap.put(downloadInfo.getId(), downloadInfo);
        }
    }

    @Override
    public void onDestroyed(long id, Downloader downloader) {
        if (mDownloaderMap.containsKey(id)) {
            mDownloaderMap.remove(id);
        }
    }

    private boolean check(long key) {
        if (mDownloaderMap.containsKey(key)) {
            Downloader downloader = mDownloaderMap.get(key);
            if (downloader != null) {
                if (downloader.isRunning()) {
                    Alog.w("Task has been started!");
                    return false;
                } else {
                    throw new IllegalStateException("Downloader instance with same tag has not been destroyed!");
                }
            }
        }
        return true;
    }

    /**
     * 判断下载的任务是否存在
     * @return
     */
    private long queryExistId(String url) {

        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("下载的参数不能为NULL!");
        }

        long id = 0L;

        List<DownloadEntity> downloadInfos = mDBDbManager.getDownloadEntityDao()
                .queryBuilder().where(
                        DownloadEntityDao.Properties.Url.eq(url)).build().list();

        if (downloadInfos != null && downloadInfos.size() > 0) {
            // 获取第一个任务
            id = downloadInfos.get(0).getId();
        }

        return id;
    }

    private DownloadEntity newDownloadInfo(String name, String dir, String url) {

        DownloadEntity downloadInfo = new DownloadEntity();

        downloadInfo.setName(name);
        downloadInfo.setTempName(name + ".tmp");
        downloadInfo.setDir(dir);
        downloadInfo.setUrl(url);
        downloadInfo.setState(DownloadStatus.STATUS_INIT);
        downloadInfo.setTime(System.currentTimeMillis());

        return downloadInfo;
    }

    @Override
    public long add(DownloadFile downloadFile) {

        if (downloadFile == null) return 0L;

        // 不做处理了
//        long id = queryExistId(downloadFile.getUri());
//
//        if (id != 0L) return id;

        DownloadEntityDao downloadInfoDao = mDBDbManager.getDownloadEntityDao();

        // 添加到列表中
        DownloadEntity downloadInfo = newDownloadInfo(
                downloadFile.getName(), downloadFile.getFolder().getPath(), downloadFile.getUri());
        long rowId = downloadInfoDao.insert(downloadInfo);

        // 添加到列表中
        mDownloadInfoMap.put(rowId, downloadInfo);

        return rowId;
    }

    @Override
    public void start(long id) {

        if (!check(id)) return ;

        DownloadEntity downloadInfo = getDownloadEntity(id);

        if (downloadInfo == null) return ;

        // 开始下载...
        DownloadResponse response = new DownloadResponseImpl(downloadInfo.getId(), mDelivery, mDownloadMonitor);
        Downloader downloader = new DownloaderImpl(downloadInfo, response, mExecutorService, mDBDbManager, mConfig, this);
        mDownloaderMap.put(id, downloader);
        downloader.start();
    }

    @Override
    public boolean isDownloading(long id) {

        if (!mDownloaderMap.containsKey(id)) return false;

        return mDownloaderMap.get(id).isRunning();
    }

    @Override
    public void pause(long id) {

        if (!mDownloaderMap.containsKey(id)) return ;

        Downloader downloader = mDownloaderMap.get(id);
        if (downloader != null && downloader.isRunning()) {
            downloader.pause();
        }
        mDownloaderMap.remove(id);
    }

    @Override
    public void cancel(long id) {

        if (!mDownloaderMap.containsKey(id)) return ;

        Downloader downloader = mDownloaderMap.get(id);
        if (downloader != null && downloader.isRunning()) {
            downloader.cancel();
        }
        mDownloaderMap.remove(id);
    }

    @Override
    public void pauseAll() {

        for (Downloader downloader : mDownloaderMap.values()) {
            if (downloader != null && downloader.isRunning()) {
                downloader.pause();
            }
        }
    }

    @Override
    public void cancelAll() {

        for (Downloader downloader : mDownloaderMap.values()) {
            if (downloader != null && downloader.isRunning()) {
                downloader.cancel();
            }
        }
    }

    @Override
    public void delete(long id) {

        if (mDownloadInfoMap.containsKey(id)) {
            // 删除列表中的信息
            mDownloadInfoMap.remove(id);
        }

        // 获取Dao
        DownloadEntityDao downloadInfoDao = mDBDbManager.getDownloadEntityDao();
        ThreadEntityDao threadInfoDao = mDBDbManager.getThreadEntityDao();

        // 获取下载信息
        DownloadEntity downloadInfo = downloadInfoDao.load(id);

        if (downloadInfo == null) return ;

        // 删除下载信息
        downloadInfoDao.deleteByKey(id);

        // 删除下载文件 - 可能还存在临时文件
        FileUtil.delete(new File(downloadInfo.getDir(), downloadInfo.getName()));
        FileUtil.delete(new File(downloadInfo.getDir(), downloadInfo.getTempName()));

        // 删除块信息
        List<ThreadEntity> threadInfos = threadInfoDao
                .queryBuilder().where(ThreadEntityDao.Properties.Tid.eq(id)).build().list();

        if (threadInfos != null && !threadInfos.isEmpty()) {
            // 删除所有记录
            threadInfoDao.deleteInTx(threadInfos);
        }
    }

    @Override
    public DownloadEntity getDownloadEntity(long id) {

        if (!mDownloadInfoMap.containsKey(id)) return null;

        return mDownloadInfoMap.get(id);
    }

    @Override
    public List<DownloadEntity> getDownloadEntities(long... ids) {

        List<DownloadEntity> downloadInfos = mDBDbManager.getDownloadEntityDao()
                .queryBuilder().where(DownloadEntityDao.Properties.Id.in(ids)).build().list();
        return downloadInfos;
    }

    @Override
    public DownloadEntity getDownloadProgress(long id) {

        List<ThreadEntity> threadInfos = mDBDbManager.getThreadEntityDao()
                .queryBuilder().where(ThreadEntityDao.Properties.Tid.eq(id)).build().list();

        if (threadInfos == null || threadInfos.isEmpty()) return null;

        long finished = 0;
        long progress;
        long total = 0;
        for (ThreadEntity info : threadInfos) {
            finished += info.getFinished();
            total += (info.getEnd() - info.getStart());
        }
        progress = finished * 100 / total;
        DownloadEntity downloadInfo = new DownloadEntity();
        downloadInfo.setFinished(finished);
        downloadInfo.setLength(total);
        downloadInfo.setProgress((int) progress);

        return downloadInfo;
    }

    @Override
    public List<DownloadEntity> getCompletes() {

        List<DownloadEntity> downloadInfos = mDBDbManager.getDownloadEntityDao()
                .queryBuilder().where(DownloadEntityDao.Properties.State.eq(DownloadStatus.STATUS_COMPLETED)).build().list();
        return downloadInfos;
    }

    @Override
    public List<DownloadEntity> getUnCompletes() {
        List<DownloadEntity> downloadInfos = mDBDbManager.getDownloadEntityDao()
                .queryBuilder().where(DownloadEntityDao.Properties.State.notEq(DownloadStatus.STATUS_COMPLETED)).build().list();
        return downloadInfos;
    }

    @Override
    public void registerDownloadListener(DownloadListener listener) {
        mDownloadMonitor.registerListener(listener);
    }

    @Override
    public void unregisterDownloadListener(DownloadListener listener) {
        mDownloadMonitor.unregisterListener(listener);
    }
}
