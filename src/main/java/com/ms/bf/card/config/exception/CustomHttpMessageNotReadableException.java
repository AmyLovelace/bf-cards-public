package com.ms.bf.card.config.exception;

import com.ms.bf.card.config.ErrorCode;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class CustomHttpMessageNotReadableException extends HttpMessageNotReadableException {

    public CustomHttpMessageNotReadableException(String msg, HttpInputMessage httpInputMessage) {
        super(msg, httpInputMessage);
    }

    public CustomHttpMessageNotReadableException(String msg, Throwable cause, HttpInputMessage httpInputMessage) {
        super(msg, cause, httpInputMessage);
    }

    public CustomHttpMessageNotReadableException(ErrorCode errorCode) {
        super(String.valueOf(errorCode.getReasons()));
    }
}

