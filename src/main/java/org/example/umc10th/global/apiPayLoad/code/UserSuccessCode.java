package org.example.umc10th.global.apiPayLoad.code;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    OK(
            HttpStatus.OK,
            "USER200_1",
            "성공적으로 유저를 조회했습니다."
    ),

    SIGNUP_SUCCESS(
            HttpStatus.CREATED,
            "USER201_1",
            "회원가입이 성공적으로 완료되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
