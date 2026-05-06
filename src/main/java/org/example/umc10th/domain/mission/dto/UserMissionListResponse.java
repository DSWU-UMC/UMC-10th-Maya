package org.example.umc10th.domain.mission.dto;

import java.util.List;

public record UserMissionListResponse(
        List<UserMissionResponse> data,
        PageInfo pageInfo
) {}
