package org.example.umc10th.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.user.converter.UserConverter;
import org.example.umc10th.domain.user.dto.MyPageResponse;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.domain.user.service.UserService;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.example.umc10th.global.apiPayLoad.code.UserSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 마이페이지
    @PostMapping("v1/users/me")
    public ApiResponse<UserResponse.GetInfo> getInfo(
            @RequestBody UserRequest.GetInfo dto
    ){
        BaseSuccessCode code=UserSuccessCode.OK;
        return ApiResponse.onSuccess(code,userService.getInfo(dto));
    }
}





