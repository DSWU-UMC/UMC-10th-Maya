package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;

public record UserReviewRequest(
        Long storeId,   // 가게 ID
        Long userId,    // 사용자 ID
        String content, // 리뷰 내용
        BigDecimal score // 리뷰 점수
) {}