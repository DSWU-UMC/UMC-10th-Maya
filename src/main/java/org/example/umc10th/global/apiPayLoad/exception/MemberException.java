package org.example.umc10th.global.apiPayLoad.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode){super(errorCode);}
}
