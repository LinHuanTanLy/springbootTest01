package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

public class CommException extends BaseException {
    public CommException(ErrCode err) {
        super(err);
    }

    public CommException(String message, ErrCode err) {
        super(message, err);
    }
}
