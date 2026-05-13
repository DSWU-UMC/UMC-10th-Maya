package org.example.umc10th.global.apiPayLoad.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class UserException extends ProjectException {
    public UserException(BaseErrorCode errorCode){super(errorCode);}
}
