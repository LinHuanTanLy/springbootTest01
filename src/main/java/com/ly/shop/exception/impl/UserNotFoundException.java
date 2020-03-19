package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(ErrCode err) {
        super(err);
    }
}
