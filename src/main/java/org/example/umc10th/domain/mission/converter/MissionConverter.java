package org.example.umc10th.domain.mission.converter;

import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;
import org.example.umc10th.domain.mission.entity.Store;


public class MissionConverter {

    //가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionRequest.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    //가게 내 미션 조회
    public static MissionResponse.GetMission toGetMission(
            Mission mission
    ){
        return MissionResponse.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    //홈 화면
    public static MissionResponse.GetHomeMission toGetHomeMission(
            Mission mission
    ){
        return MissionResponse.GetHomeMission.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .regionName(mission.getStore().getRegion().getName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .build();
    }
}
