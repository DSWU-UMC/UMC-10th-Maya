package org.example.umc10th.domain.review.repository;

import org.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 기본 조회
    List<Review> findByUserId(Long userId);

    List<Review> findByStoreId(Long storeId);


    // 내가 작성한 리뷰 조회 - ID 정렬
    // 첫 페이지
    Slice<Review> findByUserIdOrderByIdDesc(
            Long userId,
            Pageable pageable
    );

    // 다음 페이지 (cursorId 사용)
    Slice<Review> findByUserIdAndIdLessThanOrderByIdDesc(
            Long userId,
            Long cursorId,
            Pageable pageable
    );


    // 내가 작성한 리뷰 조회 - SCORE 정렬


    // 첫 페이지
    Slice<Review> findByUserIdOrderByScoreDescIdDesc(
            Long userId,
            Pageable pageable
    );

    // 다음 페이지 (복합 커서: score + id)
    @Query("""
        SELECT r
        FROM Review r
        WHERE r.user.id = :userId
        AND (
            r.score < :score
            OR (r.score = :score AND r.id < :id)
        )
        ORDER BY r.score DESC, r.id DESC
    """)
    Slice<Review> findByUserScoreCursor(
            @Param("userId") Long userId,
            @Param("score") BigDecimal score,
            @Param("id") Long id,
            Pageable pageable
    );
}