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

import java.util.List;


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
        BaseSuccessCode code= ReviewSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code,reviewService.createReview(storeId,userId,dto));

    }

    // 리뷰 조회 (가게 기준)
    @GetMapping("/store/{storeId}")
    public ApiResponse<List<ReviewResponse.GetReview>> getReviewsByStore(
            @PathVariable Long storeId
    ) {

        BaseSuccessCode code=ReviewSuccessCode.REVIEW_FETCHED;
        return ApiResponse.onSuccess(code,reviewService.getReviewsByStore(storeId));
    }
}