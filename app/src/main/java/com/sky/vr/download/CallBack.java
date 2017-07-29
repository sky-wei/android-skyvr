package com.sky.vr.download;


import java.net.HttpURLConnection;

/**
 * CallBack of download status
 */
public interface CallBack {

    void onStarted(long id);

    /**
     * <p> this will be the the first method called by
     * {@link ConnectTaskImpl}.
     */
    void onConnecting(long id);

    /**
     * <p> if {@link ConnectTaskImpl} is successfully
     * connected with the http/https server this method will be invoke. If not method
     * {@link #onFailed(long, DownloadException)} will be invoke.
     *
     * @param total          The length of the file. See {@link HttpURLConnection#getContentLength()}
     * @param isRangeSupport indicate whether download can be resumed from pause.
     *                       See {@link ConnectTaskImpl#run()}. If the value of http header field
     *                       {@code Accept-Ranges} is {@code bytes} the value of  isRangeSupport is
     *                       {@code true} else {@code false}
     */
    void onConnected(long id, long total, boolean isRangeSupport);

    /**
     * <p> progress callback.
     *
     * @param finished the downloaded length of the file
     * @param total    the total length of the file same value with method {@link }
     * @param progress the percent of progress (finished/total)*100
     */
    void onProgress(long id, long finished, long total, int progress);

    /**
     * <p> download complete
     */
    void onCompleted(long id);

    /**
     * <p> if you invoke {@link DownloadManagerImpl#pause(long)} or {@link DownloadManagerImpl#pauseAll()}
     * this method will be invoke if the downloading task is successfully paused.
     */
    void onDownloadPaused(long id);

    /**
     * <p> if you invoke {@link DownloadManagerImpl#cancel(long)} or {@link DownloadManagerImpl#cancelAll()}
     * this method will be invoke if the downloading task is successfully canceled.
     */
    void onDownloadCanceled(long id);

    /**
     * <p> download fail or exception callback
     *
     * @param e download exception
     */
    void onFailed(long id, DownloadException e);
}
