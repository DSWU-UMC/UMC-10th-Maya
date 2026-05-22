package org.example.umc10th.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.mission.repository.StoreRepository;
import org.example.umc10th.domain.review.converter.ReviewConverter;
import org.example.umc10th.domain.review.dto.*;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.review.repository.ReviewRepository;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;

import org.example.umc10th.global.apiPayLoad.code.ReviewErrorCode;
import org.example.umc10th.global.apiPayLoad.code.StoreErrorCode;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.security.exception.ReviewException;
import org.example.umc10th.global.security.exception.StoreException;
import org.example.umc10th.global.security.exception.UserException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponse.GetReview createReview(
            Long storeId,
            Long userId,
            ReviewRequest.CreateReview dto
    ) {

        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() ->
                        new StoreException(StoreErrorCode.NOT_FOUND));

        // 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserException(UserErrorCode.USER_NOT_FOUND));

        Review review =
                ReviewConverter.toReview(store, user, dto);

        reviewRepository.save(review);

        return ReviewConverter.toGetReview(review);
    }

    // =========================================
    // 내가 작성한 리뷰 조회 (커서 기반)
    // =========================================

    public ReviewResponse.Pagination<ReviewResponse.GetReview> getMyReviews(
            Long userId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        PageRequest pageRequest =
                PageRequest.of(0, pageSize);

        Slice<Review> reviewList;

        String nextCursor = "-1";

        // =========================
        // 첫 페이지 조회
        // =========================

        if (cursor.equals("-1")) {

            switch (query.toLowerCase()) {

                case "id":

                    reviewList =
                            reviewRepository
                                    .findByUserIdOrderByIdDesc(
                                            userId,
                                            pageRequest
                                    );

                    break;

                case "score":

                    reviewList =
                            reviewRepository
                                    .findByUserIdOrderByScoreDescIdDesc(
                                            userId,
                                            pageRequest
                                    );

                    break;

                default:

                    throw new ReviewException(
                            ReviewErrorCode.QUERY_NOT_VALID
                    );
            }

        }

        // =========================
        // 다음 페이지 조회
        // =========================

        else {

            String[] cursorSplit =
                    cursor.split(":");

            switch (query.toLowerCase()) {

                case "id":

                    Long idCursor =
                            Long.parseLong(cursorSplit[1]);

                    reviewList =
                            reviewRepository
                                    .findByUserIdAndIdLessThanOrderByIdDesc(
                                            userId,
                                            idCursor,
                                            pageRequest
                                    );

                    break;

                case "score":

                    BigDecimal scoreCursor =
                            new BigDecimal(cursorSplit[0]);

                    Long reviewIdCursor =
                            Long.parseLong(cursorSplit[1]);

                    reviewList =
                            reviewRepository
                                    .findByUserScoreCursor(
                                            userId,
                                            scoreCursor,
                                            reviewIdCursor,
                                            pageRequest
                                    );

                    break;

                default:

                    throw new ReviewException(
                            ReviewErrorCode.QUERY_NOT_VALID
                    );
            }
        }

        // =========================
        // 다음 커서 생성
        // =========================

        if (!reviewList.getContent().isEmpty()) {

            Review lastReview =
                    reviewList.getContent()
                            .get(reviewList.getContent().size() - 1);

            switch (query.toLowerCase()) {

                case "id":

                    nextCursor =
                            lastReview.getId()
                                    + ":" +
                                    lastReview.getId();

                    break;

                case "score":

                    nextCursor =
                            lastReview.getScore()
                                    + ":" +
                                    lastReview.getId();

                    break;
            }
        }

        return ReviewConverter.toPagination(
                reviewList
                        .map(ReviewConverter::toGetReview)
                        .toList(),

                reviewList.hasNext(),

                nextCursor,

                reviewList.getSize()
        );
    }
}

