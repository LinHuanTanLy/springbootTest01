package com.ly.shop.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public class CommResult<T> implements Serializable {

    private static final long serialVersionUID = 961235512220891746L;
    private long code;
    private String message;
    private T data;

    public CommResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommResult(String message) {
        this.message = message;
    }

    public static <T> CommResult<T> suc(T t) {
        return new CommResult<T>(ErrCode.SUC.getCode(), ErrCode.SUC.getMsg(), t);
    }

    public static <T> CommResult<T> fail(T t) {
        return new CommResult<T>(ErrCode.REQUEST_ERR.getCode(), ErrCode.REQUEST_ERR.getMsg(), t);
    }

    public static <T> CommResult<T> fail(int code, String msg) {
        return new CommResult<T>(code, msg, null);
    }

    public static CommResult<String> fail(String message) {
        return new CommResult<>(message);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
