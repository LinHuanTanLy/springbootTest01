package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

public class UserIsExistException extends BaseException {
    public UserIsExistException(ErrCode err) {
        super(err);
    }
}
