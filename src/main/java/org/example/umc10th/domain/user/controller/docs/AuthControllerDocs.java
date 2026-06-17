package org.example.umc10th.domain.user.controller.docs;

import org.example.umc10th.domain.user.dto.SignupRequest;
import org.example.umc10th.domain.user.dto.SignupResponse;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "인증 API")
public interface AuthControllerDocs {

    @Operation(
            summary = "회원가입 API",
            description = """
                    # 회원가입

                    ## 요청 형식
                    - 이메일, 비밀번호, 이름, 성별, 주소, 전화번호, 선호 음식 목록을 입력합니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "회원가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "USER201_1",
                                              "message": "회원가입에 성공했습니다.",
                                              "result": {
                                                "userId": 1,
                                                "name": "마야",
                                                "accessToken": "eyJhbGciOiJIUzI1NiJ9..."
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ResponseEntity<ApiResponse<SignupResponse>> signup(
            @RequestBody SignupRequest request
    );



    @Operation(
            summary = "로그인 API",
            description = """
                    # 로그인

                    ## 요청 형식
                    - 이메일과 비밀번호를 입력합니다.
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "USER200_1",
                                              "message": "성공적으로 로그인했습니다.",
                                              "result": {
                                                "accessToken": "eyJhbGciOiJIUzI1NiJ9..."
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "로그인 실패",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "USER401_1",
                                              "message": "이메일 또는 비밀번호가 올바르지 않습니다.",
                                              "result": null
                                            }
                                            """
                            )
                    )
            )
    })
    ResponseEntity<ApiResponse<UserResponse.LoginResponse>> login(
            @RequestBody UserRequest.LoginRequest request
    );
}