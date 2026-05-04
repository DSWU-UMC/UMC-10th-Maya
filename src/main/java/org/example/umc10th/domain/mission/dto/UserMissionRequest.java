package org.example.umc10th.domain.mission.dto;


public record UserMissionRequest(
        Long userId,
        int page,
        int size,
        boolean completed
) {}