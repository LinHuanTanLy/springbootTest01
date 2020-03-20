package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(ErrCode err) {
        super(err);
    }

    public ProductNotFoundException() {
        super(ErrCode.PRODUCT_NOT_EXIST);
    }
}
