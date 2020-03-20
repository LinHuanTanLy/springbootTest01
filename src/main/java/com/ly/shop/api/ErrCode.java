package com.ly.shop.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * 异常枚举
 */
public enum ErrCode {

    USER_NOT_EXIST(1001, HttpStatus.NOT_FOUND, "没有该用户"),


    REQUEST_ERR(1002, HttpStatus.INTERNAL_SERVER_ERROR, "请求失败"),
    METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "请求参数错误"),
    USER_IS_EXIST(1004, HttpStatus.BAD_REQUEST, "该用户已经存在了"),
    UNAUTHORIZED(1005, HttpStatus.UNAUTHORIZED, "登录信息已经失效"),
    UPDATE_ERROR(1006, HttpStatus.BAD_REQUEST, "数据更新失败"),
    ADD_ERROR(1007, HttpStatus.BAD_REQUEST, "数据添加失败"),
    PRODUCT_NOT_EXIST(1008, HttpStatus.NOT_FOUND, "商品迷路了"),
    PRODUCT_OUT_OF_STOCK(1009, HttpStatus.NOT_FOUND, "商品库存不足"),
    ORDER_SUBMIT_ERR(1010, HttpStatus.INTERNAL_SERVER_ERROR, "订单价格错误"),
    SUC(1000, HttpStatus.OK, "请求成功");
    private String msg;
    private int code;
    private HttpStatus status;


    ErrCode(int code, HttpStatus status, String msg) {
        this.msg = msg;
        this.code = code;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
