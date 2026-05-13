package org.example.umc10th.global.apiPayLoad.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
