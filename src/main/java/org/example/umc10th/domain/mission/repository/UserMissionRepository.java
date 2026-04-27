package org.example.umc10th.domain.mission.repository;

import org.example.umc10th.domain.mission.entity.Mission;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um
        FROM UserMission um
        WHERE um.user.id = :userId
        AND um.status IN :statuses
    """)
    Page<UserMission> findByUserAndStatuses(
            @Param("userId") Long userId,
            @Param("statuses") List<MissionStatus> statuses,
            Pageable pageable
    );


}