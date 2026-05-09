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

import org.example.umc10th.global.apiPayLoad.code.StoreErrorCode;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.apiPayLoad.exception.StoreException;
import org.example.umc10th.global.apiPayLoad.exception.UserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        //가게 찾기
        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreErrorCode.NOT_FOUND));

        //유저 찾기
        User user=userRepository.findById(userId)
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));

        Review review=ReviewConverter.toReview(store,user,dto);

        reviewRepository.save(review);
        return ReviewConverter.toGetReview(review);

    }
    // 리뷰 조회 (가게 기준)
    public List<ReviewResponse.GetReview> getReviewsByStore(Long storeId) {

        List<Review> reviewList = reviewRepository.findByStoreId(storeId);

        return reviewList.stream()
                .map(ReviewConverter::toGetReview)
                .toList();
    }
}


