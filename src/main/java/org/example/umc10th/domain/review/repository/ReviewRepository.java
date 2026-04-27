package org.example.umc10th.domain.review.repository;

import org.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByUserId(Long userId);
    List<Review> findByStoreId(Long storeId);
}
