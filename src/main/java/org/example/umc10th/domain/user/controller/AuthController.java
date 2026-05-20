package org.example.umc10th.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.dto.SignupRequest;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.service.AuthService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.example.umc10th.global.apiPayLoad.code.UserSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(
            @RequestBody SignupRequest request
    ) {

        BaseSuccessCode code = UserSuccessCode.SIGNUP_SUCCESS;

        return ResponseEntity.ok(
                ApiResponse.onSuccess(code, null)
        );
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse.LoginResponse>> login(
            @RequestBody UserRequest.LoginRequest request
    ) {

        String token = authService.login(request);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        UserSuccessCode.OK,
                        new UserResponse.LoginResponse(token)
                )
        );
    }
}
