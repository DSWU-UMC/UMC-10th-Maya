package org.example.umc10th.domain.user.service;


import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.user.converter.UserConverter;
import org.example.umc10th.domain.user.dto.SignupRequest;

import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.apiPayLoad.exception.UserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signup(SignupRequest request) {

        // 이메일 중복 체크
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserException(UserErrorCode.ALREADY_EXIST_USER);
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .build();

        userRepository.save(user);
    }

    // 마이페이지
    public UserResponse.GetInfo getInfo(UserRequest.GetInfo dto) {

        Long userId = dto.id();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toGetInfo(user);
    }
}
