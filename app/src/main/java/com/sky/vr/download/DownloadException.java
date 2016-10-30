package com.sky.vr.download;

/**
 * Created by Aspsine on 2015/7/15.
 */
public class DownloadException extends Exception {
    private int errorCode;


    public DownloadException() {
    }

    public DownloadException(String detailMessage) {
        super(detailMessage);
    }

    public DownloadException(int errorCode, String detailMessage) {
        this(detailMessage);
        this.errorCode = errorCode;
    }

    public DownloadException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DownloadException(int errorCode, String detailMessage, Throwable throwable) {
        this(detailMessage, throwable);
        this.errorCode = errorCode;
    }

    public DownloadException(Throwable throwable) {
        super(throwable);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
