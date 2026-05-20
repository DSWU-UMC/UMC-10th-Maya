package org.example.umc10th.global.security.exception;

import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
