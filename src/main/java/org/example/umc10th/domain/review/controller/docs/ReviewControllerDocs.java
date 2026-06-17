package org.example.umc10th.domain.review.controller.docs;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "리뷰 API")
public interface ReviewControllerDocs {

    @Operation(
            summary = "리뷰 작성 API",
            description = """
                    # 리뷰 작성
                    
                    ## 요청 형식
                    
                    - Path Variable : storeId
                    - Query Parameter : userId
                    - 리뷰 내용(content)과 평점(score)을 입력합니다.
                    
                    ## score 범위
                    
                    - 최소 1점
                    - 최대 5점
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 작성 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "REVIEW200_1",
                                              "message": "리뷰가 성공적으로 등록되었습니다.",
                                              "result": {
                                                "reviewId": 1,
                                                "userName": "마야",
                                                "content": "정말 맛있어요!",
                                                "score": 4.5,
                                                "createdAt": "2025-08-10T12:30:00"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "유효성 검사 실패",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": false,
                                              "code": "COMMON400",
                                              "message": "점수는 1 이상 5 이하이어야 합니다.",
                                              "result": null
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<ReviewResponse.GetReview> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequest.CreateReview dto
    );


    @Operation(
            summary = "내가 작성한 리뷰 조회 API",
            description = """
                    # 내가 작성한 리뷰 조회
                    
                    ## 커서 기반 페이지네이션
                    
                    ### pageSize
                    - 조회할 데이터 개수
                    
                    ### cursor
                    - 첫 요청 : -1
                    - 이후 : 응답의 nextCursor 값 사용
                    
                    ### query
                    - id : 최신순 조회
                    - score : 평점순 조회
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "REVIEW200_2",
                                              "message": "리뷰 조회에 성공했습니다.",
                                              "result": {
                                                "data": [
                                                  {
                                                    "reviewId": 1,
                                                    "userName": "마야",
                                                    "content": "정말 맛있어요!",
                                                    "score": 4.5,
                                                    "createdAt": "2025-08-10T12:30:00"
                                                  },
                                                  {
                                                    "reviewId": 2,
                                                    "userName": "마야",
                                                    "content": "재방문 의사 있습니다.",
                                                    "score": 5.0,
                                                    "createdAt": "2025-08-09T18:20:00"
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
    ApiResponse<ReviewResponse.Pagination<ReviewResponse.GetReview>>
    getMyReviews(

            @RequestParam Long userId,

            @RequestParam(defaultValue = "10")
            Integer pageSize,

            @RequestParam(defaultValue = "-1")
            String cursor,

            @RequestParam(defaultValue = "id")
            String query
    );
}