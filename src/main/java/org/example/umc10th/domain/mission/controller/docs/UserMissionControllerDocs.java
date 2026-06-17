package org.example.umc10th.domain.mission.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.example.umc10th.domain.mission.dto.UserMissionRequest;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "내 미션 API")
public interface UserMissionControllerDocs {

    @Operation(
            summary = "내 미션 조회 API",
            description = """
                    # 내가 진행 중 / 완료한 미션 조회
                    
                    ## 요청 파라미터
                    
                    - userId : 사용자 ID
                    - missionFilterStatus : 진행중(IN_PROGRESS) / 완료(COMPLETED)
                    - pageNumber : 페이지 번호 (0부터 시작)
                    - pageSize : 페이지 크기
                    - sort : 정렬 조건
                    
                    ## 예시
                    
                    /api/user-missions?
                    userId=1&
                    missionFilterStatus=IN_PROGRESS&
                    pageNumber=0&
                    pageSize=10&
                    sort=deadline
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "내 미션 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "MISSION200_1",
                                              "message": "성공적으로 조회했습니다.",
                                              "result": {
                                                "data": [
                                                  {
                                                    "userMissionId": 1,
                                                    "missionId": 10,
                                                    "storeName": "BBQ 강남점",
                                                    "conditional": "3만원 이상 주문하기",
                                                    "point": 500,
                                                    "deadline": "2025-08-31",
                                                    "status": "IN_PROGRESS"
                                                  },
                                                  {
                                                    "userMissionId": 2,
                                                    "missionId": 11,
                                                    "storeName": "교촌치킨 역삼점",
                                                    "conditional": "2만원 이상 주문하기",
                                                    "point": 300,
                                                    "deadline": "2025-08-20",
                                                    "status": "IN_PROGRESS"
                                                  }
                                                ],
                                                "pageNumber": 0,
                                                "pageSize": 10
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<
            UserMissionResponse.Pagination<
                    UserMissionResponse.getMyMission
                    >
            > getUserMissions(

            @RequestParam Long userId,

            @ModelAttribute UserMissionRequest.SortMyMission dto
    );
}
