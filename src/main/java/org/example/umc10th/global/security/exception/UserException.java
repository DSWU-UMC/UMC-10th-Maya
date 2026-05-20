package org.example.umc10th.global.security.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class UserException extends ProjectException {
    public UserException(BaseErrorCode errorCode){super(errorCode);}
}
