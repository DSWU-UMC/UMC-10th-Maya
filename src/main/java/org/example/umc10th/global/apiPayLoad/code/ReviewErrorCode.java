package org.example.umc10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "리뷰를 찾을 수 없습니다."
    ),

    QUERY_NOT_VALID(
            HttpStatus.NOT_FOUND,
            "REVIEW404_2",
                    "쿼리가 적절하지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
