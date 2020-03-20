package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

import static com.ly.shop.api.ErrCode.ORDER_SUBMIT_ERR;

public class OrderErrException extends BaseException {
    public OrderErrException() {
        super(ORDER_SUBMIT_ERR);
    }

    public OrderErrException(String message, ErrCode err) {
        super(message, err);
    }
}
