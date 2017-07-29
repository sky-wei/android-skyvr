package com.sky.vr.download;

/**
 * Created by Aspsine on 2015/7/14.
 */
public class DownloadConfiguration {

    public static final int DEFAULT_MAX_THREAD_NUMBER = 3;

    public static final int DEFAULT_THREAD_NUMBER = 1; // 注意，目前只能是1个

    /**
     * thread number in the pool
     */
    private int maxThreadNum;

    /**
     * thread number for each download
     */
    private int threadNum;


    /**
     * init with default value
     */
    public DownloadConfiguration() {
        maxThreadNum = DEFAULT_MAX_THREAD_NUMBER;
        threadNum = DEFAULT_THREAD_NUMBER;
    }

    public int getMaxThreadNum() {
        return maxThreadNum;
    }

    public void setMaxThreadNum(int maxThreadNum) {
        this.maxThreadNum = maxThreadNum;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }
}
