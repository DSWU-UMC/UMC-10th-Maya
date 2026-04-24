package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;

public record ReviewResponse(
        Long reviewId,
        Long storeId,
        String content,
        BigDecimal score
) {}