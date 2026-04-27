package org.example.umc10th.domain.mission.dto;

import org.example.umc10th.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public record UserMissionResponse(
        Long userMissionId,
        Long missionId,
        String storeName,
        String condition,
        Integer point,
        LocalDate deadline,
        MissionStatus status
) {}
