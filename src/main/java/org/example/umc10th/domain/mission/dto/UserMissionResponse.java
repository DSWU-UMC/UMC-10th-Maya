package org.example.umc10th.domain.mission.dto;


import lombok.Builder;
import org.example.umc10th.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public class UserMissionResponse{
    //내가 진행중, 진행 완료한 미션 모아 보기
    @Builder
    public record getMyMission(
            Long userMissionId,
            Long missionId,
            String storeName,
            String conditional,
            Integer point,
            LocalDate deadline,
            MissionStatus status

    ){

    }
}