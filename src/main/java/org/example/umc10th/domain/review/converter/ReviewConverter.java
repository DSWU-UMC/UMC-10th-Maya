package org.example.umc10th.domain.review.converter;

import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.review.dto.ReviewRequest;
import org.example.umc10th.domain.review.dto.ReviewResponse;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public ReviewResponse toDto(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUser().getName(),
                review.getContent(),
                review.getScore(),
                review.getCreatedAt()
        );
    }

    public Review toEntity(ReviewRequest request, User user, Store store){
        return Review.builder()
                .store(store)
                .user(user)
                .content(request.content())
                .score(request.score())
                .build();
    }
}