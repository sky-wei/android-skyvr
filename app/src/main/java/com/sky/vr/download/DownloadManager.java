package com.sky.vr.download;

import com.sky.vr.download.entity.DownloadEntity;

import java.util.List;

/**
 * Created by starrysky on 16-5-14.
 */
public interface DownloadManager {

    /**
     * 添加下载任务
     * @param downloadFile 下载文件信息
     * @return 返回保存的下载任务ID
     */
    long add(DownloadFile downloadFile);

    /**
     * 开始下载指定id的任务
     * @param id add方法返回后的任务id
     */
    void start(long id);

    /**
     * @param id add方法返回后的任务id
     * @return 返回当前是否在下载中
     */
    boolean isDownloading(long id);

    /**
     * 暂停下载指定id的任务
     * @param id add方法返回后的任务id
     */
    void pause(long id);

    /**
     * 取消下载指定id的任务
     * @param id add方法返回后的任务id
     */
    void cancel(long id);

    /**
     * 暂停所有的下载任务
     */
    void pauseAll();

    /**
     * 取消所有的下载任务
     */
    void cancelAll();

    /**
     * 删除下载的任务
     * @param id add方法返回后的任务id
     */
    void delete(long id);

    /**
     * 获取指定任务id的下载信息
     * @param id add方法返回后的任务id
     * @return 正在下载中的资源信息
     */
    DownloadEntity getDownloadEntity(long id);

    /**
     * 获取指定任务id集合的下载信息
     * @param ids add方法返回后的任务id集合
     * @return 正在下载中的资源信息集合
     */
    List<DownloadEntity> getDownloadEntities(long... ids);

    /**
     * 获取指定任务id的下载进度
     * @param id add方法返回后的任务id
     * @return 下载的信息
     */
    DownloadEntity getDownloadProgress(long id);

    /**
     * 获取所有完成的下载信息
     * @return 返回所有完成的下载信息
     */
    List<DownloadEntity> getCompletes();

    /**
     * 获取所有未完成的下载信息
     * @return 返回所有未完成的下载信息
     */
    List<DownloadEntity> getUncompletes();

    /**
     * 注册下载事件监听
     * @param listener
     */
    void registerDownloadListener(DownloadListener listener);

    /**
     * 注销下载事件监听
     * @param listener
     */
    void unregisterDownloadListener(DownloadListener listener);
}
