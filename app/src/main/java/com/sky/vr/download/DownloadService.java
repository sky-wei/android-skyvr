package com.sky.vr.download;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by starrysky on 16-5-14.
 */
public class DownloadService extends Service {

    private LocalBinder mLocalBinder = new LocalBinder();

    private DownloadManagerImpl mDownloadManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mDownloadManager == null) {

            mDownloadManager = new DownloadManagerImpl(this);
            mDownloadManager.init(new DownloadConfiguration());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mDownloadManager != null) {

            mDownloadManager.release();
            mDownloadManager = null;
        }
    }

    public class LocalBinder extends Binder {

        public DownloadManager getDownloadManager() {
            return mDownloadManager;
        }
    }
}
