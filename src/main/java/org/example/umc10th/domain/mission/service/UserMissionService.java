package org.example.umc10th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.UserMissionConverter;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.mission.enums.MissionFilterStatus;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.mission.repository.UserMissionRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.umc10th.domain.mission.enums.MissionStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    // 미션 조회 (진행중 / 완료)
    public List<UserMissionResponse.getMyMission> getUserMissions(
            Long userId,
            MissionFilterStatus status
    ) {

        List<MissionStatus> statuses = switch (status) {
            case IN_PROGRESS -> List.of(PENDING, APPROVED);
            case COMPLETED -> List.of(COMPLETED);
        };

        return userMissionRepository.findByUserAndStatuses(userId, statuses)
                .stream()
                .map(UserMissionConverter::toGetMyMission)
                .toList();
    }

    // 상태 변경 (Store에서 호출)
    public void changeStatus(Long userMissionId, MissionStatus status) {

        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow();

        switch (status) {
            case APPROVED -> userMission.approve();
            case COMPLETED -> userMission.complete();
            case PENDING -> userMission.pending();
        }
    }
}