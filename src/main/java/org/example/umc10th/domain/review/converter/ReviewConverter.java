package org.example.umc10th.domain.review.converter;


import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.entity.User;

import java.util.List;

public class ReviewConverter {
    //리뷰 작성하기
    public static Review toReview(
            Store store,
            User user,
            ReviewRequest.CreateReview dto
    ){
        return Review.builder()
                .store(store)
                .user(user)
                .content(dto.content())
                .score(dto.score())
                .build();
    }

    //리뷰 조회하기
    public static ReviewResponse.GetReview toGetReview(
            Review review
    ){
        return ReviewResponse.GetReview.builder()
                .reviewId(review.getId())
                .userName(review.getUser().getName())
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();

    }

    //페이지네이션 틀 생성
    public static <T> ReviewResponse.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){

        return ReviewResponse.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();

    }


}