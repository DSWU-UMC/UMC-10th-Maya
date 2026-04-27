package org.example.umc10th.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.dto.UserReviewRequest;
import org.example.umc10th.domain.review.dto.UserReviewResponse;
import org.example.umc10th.domain.review.service.ReviewService;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
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

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<UserReviewResponse>> createReview(
            @RequestBody UserReviewRequest request
    ) {
        // request에서 userId() 메서드를 통해 userId 추출
        Long userId = request.userId();

        // userId로 User 객체를 DB에서 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 리뷰 작성 메서드 호출 (User 객체와 UserReviewRequest를 전달)
        UserReviewResponse response = reviewService.createReview(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(response, ReviewSuccessCode.REVIEW_CREATED));
    }
}