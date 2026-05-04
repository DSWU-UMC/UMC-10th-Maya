package org.example.umc10th.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.mission.repository.StoreRepository;
import org.example.umc10th.domain.review.converter.ReviewConverter;
import org.example.umc10th.domain.review.dto.*;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.review.enums.SortType;
import org.example.umc10th.domain.review.repository.ReviewRepository;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 리뷰 작성
    public ReviewResponse createReview(ReviewRequest request, Long userId) {

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = reviewConverter.toEntity(request, user, store);

        reviewRepository.save(review);

        return reviewConverter.toDto(review);
    }

    // 내가 작성한 리뷰 조회 (Cursor Pagination)
    public ReviewCursorResponse getMyReviews(ReviewCursorRequest request) {

        int size = request.size();
        int limit = size + 1;

        List<Review> reviews;

        // =========================
        // ID 기준 정렬
        // =========================
        if (SortType.ID == SortType.valueOf(request.sortType())) {

            if (request.cursorId() == null) {
                reviews = reviewRepository.findTopByUserIdOrderByIdDesc(
                        request.userId(),
                        PageRequest.of(0, limit)
                );
            } else {
                reviews = reviewRepository.findByUserIdAndIdLessThanOrderByIdDesc(
                        request.userId(),
                        request.cursorId(),
                        PageRequest.of(0, limit)
                );
            }

            // =========================
            // SCORE 기준 정렬
            // =========================
        } else {

            if (request.cursorId() == null || request.cursorScore() == null) {
                reviews = reviewRepository.findTopByUserIdOrderByScoreDescIdDesc(
                        request.userId(),
                        PageRequest.of(0, limit)
                );
            } else {
                reviews = reviewRepository.findByScoreCursor(
                        request.userId(),
                        request.cursorScore(),
                        request.cursorId(),
                        PageRequest.of(0, limit)
                );
            }
        }

        // =========================
        // hasNext 처리
        // =========================
        boolean hasNext = reviews.size() > size;

        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        // =========================
        // DTO 변환
        // =========================
        List<ReviewResponse> data = reviews.stream()
                .map(reviewConverter::toDto)
                .toList();

        // =========================
        // next cursor 계산
        // =========================
        Review last = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1);

        Long nextCursorId = (last != null) ? last.getId() : null;
        BigDecimal nextCursorScore = (last != null) ? last.getScore() : null;

        // =========================
        // Response 반환
        // =========================
        return new ReviewCursorResponse(
                data,
                nextCursorId,
                nextCursorScore,
                hasNext
        );
    }
}