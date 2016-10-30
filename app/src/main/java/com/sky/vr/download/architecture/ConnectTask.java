package com.sky.vr.download.architecture;

import com.sky.vr.download.DownloadException;

/**
 * Created by Aspsine on 2015/10/29.
 */
public interface ConnectTask extends Runnable {

    interface OnConnectListener {
        void onConnecting();

        void onConnected(long time, long length, boolean isAcceptRanges);

        void onConnectCanceled();

        void onConnectFailed(DownloadException de);
    }

    void cancel();

    boolean isConnecting();

    boolean isConnected();

    boolean isCanceled();

    boolean isFailed();

    @Override
    void run();
}
