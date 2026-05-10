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

    List<Review> findByUserId(Long userId);

    List<Review> findByStoreId(Long storeId);

    // =========================
    // ID 정렬
    // =========================

    Slice<Review> findByStoreIdOrderByIdDesc(
            Long storeId,
            Pageable pageable
    );

    Slice<Review> findByStoreIdAndIdLessThanOrderByIdDesc(
            Long storeId,
            Long cursorId,
            Pageable pageable
    );

    // =========================
    // SCORE 정렬
    // =========================

    Slice<Review> findByStoreIdOrderByScoreDescIdDesc(
            Long storeId,
            Pageable pageable
    );

    // 복합 커서(score + id)
    @Query("""
        SELECT r
        FROM Review r
        WHERE r.store.id = :storeId
        AND (
            r.score < :score
            OR (r.score = :score AND r.id < :id)
        )
        ORDER BY r.score DESC, r.id DESC
    """)
    Slice<Review> findByScoreCursor(
            @Param("storeId") Long storeId,
            @Param("score") BigDecimal score,
            @Param("id") Long id,
            Pageable pageable
    );
}