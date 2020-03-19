package com.ly.shop.exception;

import com.ly.shop.api.ErrCode;

public abstract class BaseException extends RuntimeException {
    private ErrCode err;

    public BaseException(ErrCode err) {
        this.err = err;
    }

    public BaseException(String message, ErrCode err) {
        super(message);
        this.err = err;
    }

    public BaseException(String message, Throwable cause, ErrCode err) {
        super(message, cause);
        this.err = err;
    }

    public BaseException(Throwable cause, ErrCode err) {
        super(cause);
        this.err = err;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrCode err) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.err = err;
    }

    public ErrCode getErr() {
        return err;
    }

    public void setErr(ErrCode err) {
        this.err = err;
    }
}
