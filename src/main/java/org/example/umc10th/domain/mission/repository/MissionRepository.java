package org.example.umc10th.domain.mission.repository;

import org.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        SELECT m
        FROM Mission m
        WHERE m.store.region.id = :regionId
        AND NOT EXISTS (
            SELECT 1
            FROM UserMission um
            WHERE um.mission = m
        )
    """)
    Page<Mission> findChallengableMissions(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
