package org.example.umc10th.domain.mission.converter;

import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionConverter {

    public MissionResponse toDto(Mission mission) {
        return new MissionResponse(
                mission.getId(),
                mission.getStore().getName(),
                mission.getConditional(),
                mission.getPoint(),
                mission.getDeadline()
        );
    }
}
