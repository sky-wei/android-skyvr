package com.sky.vr.data;

/**
 * Created by sky on 16-10-12.
 */

public class DataException extends Exception {

    private int code;

    public DataException() {
    }

    public DataException(String detailMessage) {
        this(-1, detailMessage);
    }

    public DataException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    public DataException(String detailMessage, Throwable throwable) {
        this(-1, detailMessage, throwable);
    }

    public DataException(int code, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.code = code;
    }

    public DataException(Throwable throwable) {
        this(-1, throwable);
    }

    public DataException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
