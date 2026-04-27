package org.example.umc10th.domain.review.converter;

import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.review.dto.UserReviewRequest;
import org.example.umc10th.domain.review.dto.UserReviewResponse;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReviewConverter {

    public UserReviewResponse toDto(Review review) {
        return new UserReviewResponse(
                review.getId(),
                review.getUser().getName(),
                review.getContent(),
                review.getScore(),
                review.getCreatedAt()
        );
    }

    public Review toEntity(UserReviewRequest request, User user, Store store) {

        return new Review(
                store,
                user,
                request.content(),
                request.score()
        );
    }
}
