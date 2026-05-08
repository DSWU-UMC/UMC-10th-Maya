package org.example.umc10th.domain.user.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.converter.UserConverter;
import org.example.umc10th.domain.user.dto.MyPageResponse;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.apiPayLoad.exception.UserException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //마이페이지
    public UserResponse.GetInfo getInfo(UserRequest.GetInfo dto) {
        Long userId= dto.id();

        User user=userRepository.findById(userId)
                .orElseThrow(()-> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toGetInfo(user);
    }
}
