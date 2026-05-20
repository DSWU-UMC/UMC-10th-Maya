package org.example.umc10th.domain.user.converter;

import org.example.umc10th.domain.user.dto.SignupResponse;
import org.example.umc10th.domain.user.entity.User;
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
}
