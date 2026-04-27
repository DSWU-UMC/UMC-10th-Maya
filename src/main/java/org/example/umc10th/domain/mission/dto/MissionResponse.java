package org.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public record MissionResponse(
        Long missionId,
        String storeName,
        String conditional,
        Integer point,
        LocalDate deadline
) {}
