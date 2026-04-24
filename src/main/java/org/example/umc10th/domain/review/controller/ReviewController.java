package org.example.umc10th.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.ReviewSuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    // 마이페이지 리뷰 작성
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @RequestBody ReviewRequest request
    ) {

        ReviewResponse response = new ReviewResponse(
                1L,
                request.storeId(),
                request.content(),
                request.score()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(response, ReviewSuccessCode.REVIEW_CREATED));
    }
}
