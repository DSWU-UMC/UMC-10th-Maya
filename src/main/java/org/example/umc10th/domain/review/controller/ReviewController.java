package org.example.umc10th.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.review.dto.ReviewCursorRequest;
import org.example.umc10th.domain.review.dto.ReviewCursorResponse;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.service.ReviewService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.ReviewSuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @RequestBody @Valid ReviewRequest request
    ) {

        Long userId = request.userId();

        ReviewResponse response = reviewService.createReview(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(response, ReviewSuccessCode.REVIEW_CREATED));
    }

    // 내가 작성한 리뷰 조회 (Cursor Pagination)
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<ReviewCursorResponse>> getMyReviews(
            ReviewCursorRequest request
    ) {

        ReviewCursorResponse response = reviewService.getMyReviews(request);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(response, ReviewSuccessCode.REVIEW_FETCHED)
        );
    }
}