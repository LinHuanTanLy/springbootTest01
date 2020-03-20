package com.ly.shop.exception.impl;

import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.BaseException;

/**
 * 脱销
 */
public class ProductOutOfException extends BaseException {
    public ProductOutOfException(ErrCode err) {
        super(err);
    }

    public ProductOutOfException() {
        super(ErrCode.PRODUCT_OUT_OF_STOCK);
    }
}
