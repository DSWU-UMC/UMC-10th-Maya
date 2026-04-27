package org.example.umc10th.domain.user.controller;

import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.user.converter.MyPageConverter;
import org.example.umc10th.domain.user.dto.MyPageResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.domain.user.service.UserService;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.UserSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final MyPageConverter myPageConverter;
    // 마이페이지 조회
    @GetMapping("/mypage")
    public ResponseEntity<ApiResponse<MyPageResponse>> getMyPage() {

        Long userId = 1L; // (임시) 실제로는 로그인 사용자 ID

        User user = userService.getUser(userId);

        MyPageResponse response = myPageConverter.toDto(user);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(response, UserSuccessCode.OK)
        );
    }
}



