package org.example.umc10th.global.security.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
