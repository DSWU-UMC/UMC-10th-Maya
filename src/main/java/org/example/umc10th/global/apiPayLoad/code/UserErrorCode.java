package org.example.umc10th.global.apiPayLoad.code;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    ALREADY_EXIST_USER(
            HttpStatus.CONFLICT,
            "USER409_1",
            "이미 존재하는 이메일입니다."
    ),
    NOT_SUPPORT_SOCIAL_PROVIDER(
            HttpStatus.BAD_REQUEST,
            "USER400_1",
            "지원하지 않는 소셜 로그인 제공자입니다."
    );
    private final HttpStatus status;
    private final String code;
    private final String message;



}
