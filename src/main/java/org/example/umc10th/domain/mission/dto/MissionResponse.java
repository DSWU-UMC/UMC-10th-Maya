package org.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;


public class MissionResponse{

        //가게 내 미션 조회
        @Builder
        public record GetMission(
                Long missionId,
                Integer point,
                String conditional
        ) {}

        //홈 화면
        @Builder
        public record GetHomeMission(
                Long missionId,
                String storeName,
                String regionName,
                String conditional,
                Integer point,
                LocalDate deadline
        ){}
        //페이지네이션 틀
        @Builder
        public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
        ){}


}


