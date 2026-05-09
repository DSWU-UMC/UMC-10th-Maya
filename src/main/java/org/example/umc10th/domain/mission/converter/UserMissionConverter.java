package org.example.umc10th.domain.mission.converter;


import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.entity.UserMission;

import java.util.List;

public class UserMissionConverter {

    //내가 진행중, 진행 완료한 미션 모아 보기
    public static UserMissionResponse.getMyMission toGetMyMission(
            UserMission userMission
    ){
        return UserMissionResponse.getMyMission.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName())
                .conditional(userMission.getMission().getConditional())
                .point(userMission.getMission().getPoint())
                .deadline(userMission.getMission().getDeadline())
                .status(userMission.getStatus())
                .build();
    }
    //오프셋 기반 페이지네이션
    public static <T> UserMissionResponse.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return UserMissionResponse.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

    }

}
