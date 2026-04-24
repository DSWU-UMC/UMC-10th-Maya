package org.example.umc10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(
            HttpStatus.CREATED,
            "REVIEW200_1",
            "리뷰가 성공적으로 작성되었습니다."
    ),

    REVIEW_FETCHED(
            HttpStatus.OK,
            "REVIEW200_2",
            "리뷰 조회에 성공했습니다."
    ),

    REVIEW_DELETED(
            HttpStatus.OK,
            "REVIEW200_3",
            "리뷰 삭제에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}