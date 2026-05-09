package org.example.umc10th.domain.review.converter;


import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.entity.User;

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


}