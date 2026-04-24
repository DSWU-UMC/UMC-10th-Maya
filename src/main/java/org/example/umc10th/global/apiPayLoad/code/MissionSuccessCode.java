package org.example.umc10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_FETCHED(
            HttpStatus.OK,
            "MISSION200_1",
            "미션 조회에 성공했습니다."
    ),

    MISSION_PENDING(
            HttpStatus.OK,
            "MISSION200_2",
            "미션 요청이 완료되었습니다."
    ),

    MISSION_APPROVED(
            HttpStatus.OK,
            "MISSION200_3",
            "미션이 승인되었습니다."
    ),

    MISSION_COMPLETED(
            HttpStatus.OK,
            "MISSION200_4",
            "미션이 완료 처리되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
