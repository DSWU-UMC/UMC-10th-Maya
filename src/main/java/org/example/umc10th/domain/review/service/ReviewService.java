package org.example.umc10th.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.mission.repository.StoreRepository;
import org.example.umc10th.domain.review.converter.UserReviewConverter;
import org.example.umc10th.domain.review.dto.UserReviewRequest;
import org.example.umc10th.domain.review.dto.UserReviewResponse;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.review.repository.ReviewRepository;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserReviewConverter userReviewConverter;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 리뷰 작성
    public UserReviewResponse createReview(UserReviewRequest request, Long userId) {
        // storeId로 Store 객체를 DB에서 조회
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // userId로 User 객체를 DB에서 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Review 엔티티로 변환 (User 객체를 넘김)
        Review review = userReviewConverter.toEntity(request, user, store);

        // Review를 저장
        reviewRepository.save(review);

        // 저장된 Review 엔티티를 DTO로 변환하여 반환
        return userReviewConverter.toDto(review);
    }
}