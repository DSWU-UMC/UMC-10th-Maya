package org.example.umc10th.domain.user.service;


import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.user.converter.UserConverter;
import org.example.umc10th.domain.user.dto.SignupRequest;

import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.security.entity.AuthUser;
import org.example.umc10th.global.security.exception.UserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    // 마이페이지
    public UserResponse.GetInfo getInfo(AuthUser user) {

        //컨버터를 이용해서 응답 dto 생성& return
        return UserConverter.toGetInfo(user.getUser());
    }


}
