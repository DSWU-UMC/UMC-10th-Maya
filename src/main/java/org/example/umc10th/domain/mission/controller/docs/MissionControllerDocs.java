package org.example.umc10th.domain.mission.controller.docs;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "미션 API")
public interface MissionControllerDocs {

    @Operation(
            summary = "가게 미션 생성 API",
            description = """
                    # 가게 미션 생성
                    
                    ## 요청 형식
                    
                    - storeId : 가게 ID
                    - deadline : 미션 마감일
                    - point : 보상 포인트
                    - conditional : 미션 조건
                    
                    ## 예시
                    
                    "3만원 이상 주문하기"
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 생성 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "MISSION201_1",
                                              "message": "미션 생성에 성공했습니다.",
                                              "result": null
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionRequest.CreateMission dto
    );


    @Operation(
            summary = "가게 미션 목록 조회 API",
            description = """
                    # 가게 내 미션 조회
                    
                    ## 커서 기반 페이지네이션
                    
                    ### pageSize
                    조회 개수
                    
                    ### cursor
                    첫 요청 시 -1
                    
                    ### query
                    - id : 최신순
                    - point : 포인트순
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "MISSION200_1",
                                              "message": "미션 조회에 성공했습니다.",
                                              "result": {
                                                "data": [
                                                  {
                                                    "missionId": 1,
                                                    "point": 500,
                                                    "conditional": "3만원 이상 주문하기"
                                                  },
                                                  {
                                                    "missionId": 2,
                                                    "point": 1000,
                                                    "conditional": "5만원 이상 주문하기"
                                                  }
                                                ],
                                                "hasNext": true,
                                                "nextCursor": "2",
                                                "pageSize": 10
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<MissionResponse.Pagination<MissionResponse.GetMission>>
    getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    );
}
