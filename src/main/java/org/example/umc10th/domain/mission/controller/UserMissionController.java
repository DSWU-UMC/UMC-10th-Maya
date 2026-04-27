package org.example.umc10th.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.UserMissionConverter;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.mission.service.UserMissionService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/user-missions")
@RestController
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;
    private final UserMissionConverter userMissionConverter;


    // 미션 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserMissionResponse>>> getUserMissions(
            @RequestParam(defaultValue = "false") boolean completed,
            Pageable pageable
    ) {

        Long userId = 1L;

        Page<UserMission> missions =
                userMissionService.getUserMissions(userId, completed, pageable);

        List<UserMissionResponse> response = new ArrayList<>();

        for (UserMission mission : missions) {
            response.add(userMissionConverter.toDto(mission));
        }

        return ResponseEntity.ok(
                ApiResponse.onSuccess(response, MissionSuccessCode.MISSION_FETCHED)
        );
    }
}