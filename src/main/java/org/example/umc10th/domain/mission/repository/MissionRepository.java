package org.example.umc10th.domain.mission.repository;

import org.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {


    List<Mission> findAllByStore_Id(Long storeId);

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
}
