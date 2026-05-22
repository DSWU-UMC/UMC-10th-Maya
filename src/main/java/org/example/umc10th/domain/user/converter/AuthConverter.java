package org.example.umc10th.domain.user.converter;

import org.example.umc10th.domain.user.dto.SignupResponse;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    // 회원가입 응답
    public static SignupResponse toSignupResponse(User user, String token) {
        return new SignupResponse(
                user.getId(),
                user.getName(),
                token
        );
    }

    // 로그인 응답
    public static UserResponse.LoginResponse toLoginResponse(
            String accessToken
    ) {
        return new UserResponse.LoginResponse(accessToken);
    }
}
