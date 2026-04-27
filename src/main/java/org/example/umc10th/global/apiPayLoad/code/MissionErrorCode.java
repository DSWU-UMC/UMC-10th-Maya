package org.example.umc10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 미션을 찾을 수 없습니다."
    ),

    USER_MISSION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "해당 사용자 미션을 찾을 수 없습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
