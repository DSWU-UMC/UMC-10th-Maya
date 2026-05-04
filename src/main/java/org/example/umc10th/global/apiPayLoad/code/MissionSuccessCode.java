package org.example.umc10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."),

    OK(
            HttpStatus.OK,
            "MiSSION200_2",
            "성공적으로 미션을 조회했습니다."),

    MISSION_PENDING(
            HttpStatus.OK,
            "MISSION200_3",
            "미션 요청이 완료되었습니다."
    ),

    MISSION_APPROVED(
            HttpStatus.OK,
            "MISSION200_4",
            "미션이 승인되었습니다."
    ),

    MISSION_COMPLETED(
            HttpStatus.OK,
            "MISSION200_5",
            "미션이 완료 처리되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
