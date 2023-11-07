package com.ms.bf.card.config.exception;

import com.ms.bf.card.config.ErrorCode;

public class HttpMessageNotReadableException extends GenericException {


    protected HttpMessageNotReadableException(ErrorCode errorCode) {
            super(errorCode);
        }

}
