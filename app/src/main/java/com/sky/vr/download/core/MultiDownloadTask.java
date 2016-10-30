package com.sky.vr.download.core;

/**
 * Created by Aspsine on 2015/7/20.
 */

import com.sky.vr.download.DownloadDB;
import com.sky.vr.download.architecture.DownloadTask;
import com.sky.vr.download.entity.DownloadEntity;
import com.sky.vr.download.entity.ThreadEntity;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * download thread
 */
public class MultiDownloadTask extends DownloadTaskImpl {

    private DownloadDB mDBManager;

    public MultiDownloadTask(DownloadEntity downloadEntity, ThreadEntity threadEntity, DownloadDB downloadDB, DownloadTask.OnDownloadListener listener) {
        super(downloadEntity, threadEntity, listener);
        mDBManager = downloadDB;
    }


    @Override
    protected void insertIntoDB(ThreadEntity info) {

        ThreadEntity threadInfo = mDBManager
                .getThreadEntityDao().load(info.getId());

        if (threadInfo != null) return ;

        mDBManager.getThreadEntityDao().insert(info);
    }

    @Override
    protected int getResponseCode() {
        return HttpURLConnection.HTTP_PARTIAL;
    }

    @Override
    protected void updateDB(ThreadEntity info) {
        mDBManager.getThreadEntityDao().update(info);
    }

    @Override
    protected Map<String, String> getHttpHeaders(ThreadEntity info) {
        Map<String, String> headers = new HashMap<String, String>();
        long start = info.getStart() + info.getFinished();
        long end = info.getEnd();
        headers.put("Range", "bytes=" + start + "-" + end);
        return headers;
    }

    @Override
    protected RandomAccessFile getFile(File dir, String name, long offset) throws IOException {
        if (!dir.isDirectory()) dir.mkdirs();
        File file = new File(dir, name);
        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        raf.seek(offset);
        return raf;
    }


    @Override
    protected String getTag() {
        return this.getClass().getSimpleName();
    }
}