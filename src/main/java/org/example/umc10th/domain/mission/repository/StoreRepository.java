package org.example.umc10th.domain.mission.repository;

import org.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // storeId로 Store 엔티티를 조회하는 메서드
    Optional<Store> findById(Long storeId);
}