package com.sky.vr.download.core;


import com.sky.vr.download.architecture.DownloadTask;
import com.sky.vr.download.entity.DownloadEntity;
import com.sky.vr.download.entity.ThreadEntity;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Created by Aspsine on 2015/7/22.
 */
public class SingleDownloadTask extends DownloadTaskImpl {

    public SingleDownloadTask(DownloadEntity downloadEntity, ThreadEntity threadEntity, DownloadTask.OnDownloadListener mOnDownloadListener) {
        super(downloadEntity, threadEntity, mOnDownloadListener);
    }

    @Override
    protected void insertIntoDB(ThreadEntity info) {
        // don't support
    }

    @Override
    protected int getResponseCode() {
        return HttpURLConnection.HTTP_OK;
    }

    @Override
    protected void updateDB(ThreadEntity info) {
        // needn't Override this
    }

    @Override
    protected Map<String, String> getHttpHeaders(ThreadEntity info) {
        // simply return null
        return null;
    }

    @Override
    protected RandomAccessFile getFile(File dir, String name, long offset) throws IOException {
        if (!dir.isDirectory()) dir.mkdirs();
        File file = new File(dir, name);
        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        raf.seek(0);
        return raf;
    }

    @Override
    protected String getTag() {
        return this.getClass().getSimpleName();
    }
}

