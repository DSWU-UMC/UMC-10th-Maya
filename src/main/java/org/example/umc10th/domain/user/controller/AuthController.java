package org.example.umc10th.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.dto.SignupRequest;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
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

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(
            @RequestBody SignupRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.onSuccess(null,UserSuccessCode.SIGNUP_SUCCESS)
        );
    }
}
