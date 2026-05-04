package org.example.umc10th.global.apiPayLoad.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
