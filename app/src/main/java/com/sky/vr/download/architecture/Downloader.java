package com.sky.vr.download.architecture;

/**
 * Created by Aspsine on 2015/10/29.
 */
public interface Downloader {

    interface OnDownloaderDestroyedListener {
        void onDestroyed(long id, Downloader downloader);
    }

    boolean isRunning();

    void start();

    void pause();

    void cancel();

    void onDestroy();

}
