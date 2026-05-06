package org.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionRequest {

    //가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional

    ){}
}
