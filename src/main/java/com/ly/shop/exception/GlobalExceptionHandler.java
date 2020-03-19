package com.ly.shop.exception;


import com.ly.shop.api.CommResult;
import com.ly.shop.api.ErrCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ly.shop.api.ErrCode.METHOD_ARGUMENT_NOT_VALID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获一般错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommResult<ErrCode> handleAppException(BaseException ex) {

        ErrCode errCode = ex.getErr();
        if (errCode != null) {
            return CommResult.fail(errCode.getCode(), errCode.getMsg());
        }
        return CommResult.fail(ex.getErr());
    }

    /**
     * 捕获校验错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommResult<ErrCode> handleAppException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            return CommResult.fail(ErrCode.REQUEST_ERR.getCode(), fieldError.getDefaultMessage());
        } else {
            return CommResult.fail(METHOD_ARGUMENT_NOT_VALID);
        }


    }
}
