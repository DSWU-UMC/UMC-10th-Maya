package org.example.umc10th.domain.mission.converter;

import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.springframework.stereotype.Component;

@Component
public class UserMissionConverter {

    public UserMissionResponse toDto(UserMission userMission) {

        Mission mission = userMission.getMission();

        return new UserMissionResponse(
                userMission.getId(),
                mission.getId(),
                mission.getStore().getName(),
                mission.getConditional(),
                mission.getPoint(),
                mission.getDeadline(),
                userMission.getStatus()
        );
    }
}
