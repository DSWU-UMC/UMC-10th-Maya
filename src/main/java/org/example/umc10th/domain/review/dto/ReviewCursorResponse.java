package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public record ReviewCursorResponse(
        List<ReviewResponse> data,
        Long nextCursorId,
        BigDecimal nextCursorScore,
        boolean hasNext
) {}