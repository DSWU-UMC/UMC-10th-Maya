package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;

public record ReviewRequest(
        Long storeId,
        Long userId,
        String content,
        BigDecimal score    //클라이언트가 주는 정보
) {}
