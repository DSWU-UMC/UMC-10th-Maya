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

        Long userId = request.userId();
        Long cursorId = request.cursorId();
        BigDecimal cursorScore = request.cursorScore();
        int size = request.size();
        String sortType = request.sortType();

        int limit = size + 1;

        List<Review> reviews;

        if ("ID".equals(sortType)) {

            // 첫 페이지
            if (cursorId == null) {
                reviews = reviewRepository
                        .findByUserIdOrderByIdDesc(userId, PageRequest.of(0, limit));
            }
            // 다음 페이지
            else {
                reviews = reviewRepository
                        .findByUserIdAndIdLessThanOrderByIdDesc(userId, cursorId, PageRequest.of(0, limit));
            }

        } else { // SCORE

            // 첫 페이지
            if (cursorId == null || cursorScore == null) {
                reviews = reviewRepository
                        .findByUserIdOrderByScoreDescIdDesc(userId, PageRequest.of(0, limit));
            }
            // 다음 페이지
            else {
                reviews = reviewRepository
                        .findByScoreCursor(userId, cursorScore, cursorId, PageRequest.of(0, limit));
            }
        }

        boolean hasNext = reviews.size() > size;

        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        List<ReviewResponse> data = reviews.stream()
                .map(reviewConverter::toDto)
                .toList();

        Review last = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1);

        return new ReviewCursorResponse(
                data,
                last != null ? last.getId() : null,
                last != null ? last.getScore() : null,
                hasNext
        );
    }
}