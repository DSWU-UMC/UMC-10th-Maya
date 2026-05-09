package org.example.umc10th.domain.review.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ReviewRequest {
    //리뷰 작성하기
    public record CreateReview(


            @NotBlank(message = "리뷰 내용은 비어있을 수 없습니다.")
            String content,

            @NotNull(message = "점수는 null일 수 없습니다.")
            @DecimalMin(value = "1", message = "점수는 1 이상이어야 합니다.") // 최소 1 이상
            @DecimalMax(value = "5", message = "점수는 5 이하이어야 합니다.") // 최대 5 이하
            BigDecimal score  // 점수는 1 ~ 5 사이여야 함

    ){}

}
