package org.example.umc10th.global.apiPayLoad.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class ProjectException extends RuntimeException{
    private final BaseErrorCode errorCode;
}
