package org.example.umc10th.domain.review.dto;

import java.math.BigDecimal;

public  record ReviewCursorRequest(
        Long userId,
        Long cursorId,
        BigDecimal cursorScore,
        int size,
        String sortType   // id나 혹은 별점
) {}
