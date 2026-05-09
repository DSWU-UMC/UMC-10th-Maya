package org.example.umc10th.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.dto.UserMissionRequest;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.service.UserMissionService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/user-missions")
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;

    // 미션 조회 (진행중 / 완료)
    @GetMapping
    public ApiResponse<UserMissionResponse.Pagination<UserMissionResponse.getMyMission>> getUserMissions(
            @RequestParam Long userId,
            @ModelAttribute UserMissionRequest.SortMyMission dto
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.OK,
                userMissionService.getUserMissions(
                        userId,
                        dto.pageSize(),
                        dto.pageNumber(),
                        dto.missionFilterStatus(),
                        dto.sort()
                )
        );
    }
}