package org.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


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

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}