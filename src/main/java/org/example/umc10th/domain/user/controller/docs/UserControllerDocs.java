package org.example.umc10th.domain.user.controller.docs;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.security.entity.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Tag(name="마이페이지 API")
public interface UserControllerDocs {
    //마이페이지
    @Operation(
            summary="마이페이지 API By 마야",
            description = """
                    #마이페이지
                    
                    ##요청 형식
                    -헤더: Authorize: Bearer {JWT 토큰}
                    """
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 예시",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "isSuccess": true,
                                      "code": "USER200_1",
                                      "message": "성공적으로 유저를 조회했습니다.",
                                      "result": {
                                        "name": "마야",
                                        "profileUrl": "https://~~",
                                        "email": "mayaumc@gmail.com",
                                        "phoneNumber": "010-XXXX-XXXX",
                                        "point": 0
                                      }
                                    }
                                    """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "실패-헤더에 JWT 토큰 미삽입",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "isSuccess": false,
                                      "code": "COMMON401_1",
                                      "message": "인증되지 않았습니다.",
                                      "result": null
                                    }
                                    """
                            )
                    )
            )

    })
    ApiResponse<UserResponse.GetInfo> getInfo(@AuthenticationPrincipal AuthUser user);

}
