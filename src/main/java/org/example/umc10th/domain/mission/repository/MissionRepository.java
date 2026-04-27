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
    LEFT JOIN UserMission um ON um.mission = m
    WHERE m.store.region.id = :regionId
    AND um.id IS NULL
""")
    Page<Mission> findChallengableMissions(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
