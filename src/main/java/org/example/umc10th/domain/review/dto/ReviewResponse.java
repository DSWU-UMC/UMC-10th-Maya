package org.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class ReviewResponse {
    //리뷰 조회하기
    @Builder
    public record GetReview(
            Long reviewId,
            String userName,
            String content,
            BigDecimal score,
            LocalDateTime createdAt
    ){}
}