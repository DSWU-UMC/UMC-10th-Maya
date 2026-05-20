package org.example.umc10th.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.security.entity.AuthUser;
import org.example.umc10th.global.security.exception.UserException;
import org.example.umc10th.global.security.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(UserRequest.LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성
        return jwtUtil.createAccessToken(
                new AuthUser(user)
        );
    }
}
