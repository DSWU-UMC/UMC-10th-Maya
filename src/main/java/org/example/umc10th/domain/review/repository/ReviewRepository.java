package org.example.umc10th.domain.review.repository;

import org.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
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


    // ID 정렬
    List<Review> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);

    List<Review> findByUserIdAndIdLessThanOrderByIdDesc(
            Long userId, Long cursorId, Pageable pageable);


    // SCORE 정렬
    List<Review> findByUserIdOrderByScoreDescIdDesc(Long userId, Pageable pageable);

    //  커서 기반 쿼리
    @Query("""
    SELECT r FROM Review r
    WHERE r.user.id = :userId
    AND (
        r.score < :score
        OR (r.score = :score AND r.id < :id)
    )
    ORDER BY r.score DESC, r.id DESC
    """)
    List<Review> findByScoreCursor(
            Long userId,
            BigDecimal score,
            Long id,
            Pageable pageable
    );
}
