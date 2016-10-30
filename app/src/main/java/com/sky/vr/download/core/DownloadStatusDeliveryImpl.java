package com.sky.vr.download.core;

import android.os.Handler;

import com.sky.vr.download.CallBack;
import com.sky.vr.download.DownloadException;
import com.sky.vr.download.architecture.DownloadStatus;
import com.sky.vr.download.architecture.DownloadStatusDelivery;

import java.util.concurrent.Executor;

/**
 * Created by Aspsine on 2015/7/15.
 */
public class DownloadStatusDeliveryImpl implements DownloadStatusDelivery {
    private Executor mDownloadStatusPoster;

    public DownloadStatusDeliveryImpl(final Handler handler) {
        mDownloadStatusPoster = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
    }

    @Override
    public void post(DownloadStatus status) {
        mDownloadStatusPoster.execute(new DownloadStatusDeliveryRunnable(status));
    }

    private static class DownloadStatusDeliveryRunnable implements Runnable {
        private final DownloadStatus mDownloadStatus;
        private final CallBack mCallBack;

        public DownloadStatusDeliveryRunnable(DownloadStatus downloadStatus) {
            this.mDownloadStatus = downloadStatus;
            this.mCallBack = mDownloadStatus.getCallBack();
        }

        @Override
        public void run() {
            switch (mDownloadStatus.getStatus()) {
                case DownloadStatus.STATUS_CONNECTING:
                    mCallBack.onConnecting(mDownloadStatus.getId());
                    break;
                case DownloadStatus.STATUS_CONNECTED:
                    mCallBack.onConnected(mDownloadStatus.getId(), mDownloadStatus.getLength(), mDownloadStatus.isAcceptRanges());
                    break;
                case DownloadStatus.STATUS_PROGRESS:
                    mCallBack.onProgress(mDownloadStatus.getId(), mDownloadStatus.getFinished(), mDownloadStatus.getLength(), mDownloadStatus.getPercent());
                    break;
                case DownloadStatus.STATUS_COMPLETED:
                    mCallBack.onCompleted(mDownloadStatus.getId());
                    break;
                case DownloadStatus.STATUS_PAUSED:
                    mCallBack.onDownloadPaused(mDownloadStatus.getId());
                    break;
                case DownloadStatus.STATUS_CANCELED:
                    mCallBack.onDownloadCanceled(mDownloadStatus.getId());
                    break;
                case DownloadStatus.STATUS_FAILED:
                    mCallBack.onFailed(mDownloadStatus.getId(), (DownloadException) mDownloadStatus.getException());
                    break;
            }
        }
    }
}
