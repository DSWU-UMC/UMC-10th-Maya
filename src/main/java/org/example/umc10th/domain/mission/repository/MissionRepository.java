package org.example.umc10th.domain.mission.repository;

import org.example.umc10th.domain.mission.entity.Mission;
import org.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {


    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    @Query("""
    SELECT m
    FROM Mission m
    WHERE m.store.region.id = :regionId
    AND NOT EXISTS (
        SELECT um
        FROM UserMission um
        WHERE um.mission = m
        AND um.user.id = :userId
    )
""")
    List<Mission> findAvailableMissions(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId
    );

    //커서 기반 조회

    Slice<Mission> findByStore_IdAndIdLessThanOrderByIdDesc(
            Long storeId,
            Long idCursor,
            Pageable pageable
    );

    // 커서 없이 첫 조회
    Slice<Mission> findMissionByStore_IdOrderByIdDesc(
            Long storeId,
            PageRequest pageRequest
    );


}
