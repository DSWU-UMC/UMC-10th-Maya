package org.example.umc10th.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.user.controller.docs.UserControllerDocs;
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
import org.example.umc10th.global.security.entity.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name="마이페이지 API")
public class UserController implements UserControllerDocs {

    private final UserService userService;


    // 마이페이지
    @GetMapping("v1/users/me")
    public ApiResponse<UserResponse.GetInfo> getInfo(
            @AuthenticationPrincipal AuthUser user
    ){
        BaseSuccessCode code=UserSuccessCode.OK;
        return ApiResponse.onSuccess(code,userService.getInfo(user));
    }



}





