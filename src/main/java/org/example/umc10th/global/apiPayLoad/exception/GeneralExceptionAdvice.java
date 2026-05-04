package org.example.umc10th.global.apiPayLoad.exception;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseErrorCode;
import org.example.umc10th.global.apiPayLoad.code.GeneralErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // @Valid 어노테이션 검증 실패 예외
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        // 검증 실패한 변수명과 실패 이유를 담을 Map
        Map<String, String> errors = new HashMap<>();

        // 검증 실패한 필드별 오류 메시지
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        // 각 필드의 오류 메시지를 Map에 추가
        fieldErrors.forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        // 응답 코드: BAD_REQUEST (400)
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;

        // ApiResponse를 통해 실패한 오류 메시지들 응답
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errors)); // ApiResponse는 실패 메시지와 에러를 응답
    }
}

