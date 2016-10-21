package com.sky.vr.data;

/**
 * Created by sky on 16-10-16.
 */

public class DataException extends RuntimeException {

    public static final int CODE_UNKNOWN = 0x0000;
    public static final int CODE_CUSTOM = 0x0001;

    private int code;

    public DataException(int code) {
        this.code = code;
    }

    public DataException(String detailMessage, int code) {
        super(detailMessage);
        this.code = code;
    }

    public DataException(String detailMessage, Throwable throwable, int code) {
        super(detailMessage, throwable);
        this.code = code;
    }

    public DataException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
