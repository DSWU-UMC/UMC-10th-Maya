package org.example.umc10th.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.service.ReviewService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;

import org.example.umc10th.global.apiPayLoad.code.ReviewSuccessCode;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성


    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponse.GetReview> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewRequest.CreateReview dto
    ) {

        BaseSuccessCode code =
                ReviewSuccessCode.REVIEW_CREATED;

        return ApiResponse.onSuccess(
                code,
                reviewService.createReview(
                        storeId,
                        userId,
                        dto
                )
        );
    }


    // 내가 작성한 리뷰 조회
    // 커서 기반 페이지네이션

    @GetMapping("/my")
    public ApiResponse<
            ReviewResponse.Pagination<ReviewResponse.GetReview>> getMyReviews(

            @RequestParam Long userId,

            @RequestParam(defaultValue = "10")
            Integer pageSize,

            @RequestParam(defaultValue = "-1")
            String cursor,

            // id 또는 score
            @RequestParam(defaultValue = "id")
            String query
    ) {

        BaseSuccessCode code =
                ReviewSuccessCode.REVIEW_FETCHED;

        return ApiResponse.onSuccess(
                code,
                reviewService.getMyReviews(
                        userId,
                        pageSize,
                        cursor,
                        query
                )
        );
    }
}