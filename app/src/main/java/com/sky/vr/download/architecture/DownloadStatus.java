package com.sky.vr.download.architecture;

import com.sky.vr.download.CallBack;
import com.sky.vr.download.DownloadException;

/**
 * Created by Aspsine on 2015/7/15.
 */
public class DownloadStatus {

    public static final int STATUS_INIT = 100;
    public static final int STATUS_STARTED = 101;
    public static final int STATUS_CONNECTING = 102;
    public static final int STATUS_CONNECTED = 103;
    public static final int STATUS_PROGRESS = 104;
    public static final int STATUS_COMPLETED = 105;
    public static final int STATUS_PAUSED = 106;
    public static final int STATUS_CANCELED = 107;
    public static final int STATUS_FAILED = 108;

    private long id;
    private int status;
    private long time;
    private long length;
    private long finished;
    private int percent;
    private boolean acceptRanges;
    private DownloadException exception;

    private CallBack callBack;

    public DownloadStatus(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isAcceptRanges() {
        return acceptRanges;
    }

    public void setAcceptRanges(boolean acceptRanges) {
        this.acceptRanges = acceptRanges;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(DownloadException exception) {
        this.exception = exception;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
