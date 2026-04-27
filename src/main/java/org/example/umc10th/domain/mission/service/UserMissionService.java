package org.example.umc10th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.mission.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;


    // 미션 조회 (진행중 / 완료)

    public Page<UserMission> getUserMissions(
            Long userId,
            boolean completed,
            Pageable pageable
    ) {

        List<MissionStatus> statuses;

        if (completed) {
            statuses = List.of(MissionStatus.COMPLETED);
        } else {
            statuses = List.of(
                    MissionStatus.PENDING,
                    MissionStatus.APPROVED
            );
        }

        return userMissionRepository.findByUserAndStatuses(
                userId,
                statuses,
                pageable
        );
    }


    // 상태 변경 (Store에서 호출)

    public void changeStatus(Long userMissionId, MissionStatus status) {

        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(); // 단순 처리 (에러 코드는 Controller에서 처리)

        userMission.setStatus(status);
    }
}