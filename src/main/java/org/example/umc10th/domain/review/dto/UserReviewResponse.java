package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserReviewResponse(
        Long reviewId,
        String userName,
        String content,
        BigDecimal score,
        LocalDateTime createdAt
) {}
