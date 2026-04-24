package org.example.umc10th.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.enums.MissionStatus;

import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/user-missions")
@RestController
@RequiredArgsConstructor
public class UserMissionController {

    // 홈 화면
    @GetMapping("/home")
    public ResponseEntity<ApiResponse<List<UserMissionResponse>>> getHomeMissions(
            @RequestParam String region,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        List<UserMissionResponse> response = List.of(
                new UserMissionResponse(
                        1L,
                        "스타벅스",
                        "아메리카노 3잔 마시기",
                        1000,
                        LocalDate.now(),
                        MissionStatus.PENDING
                )
        );

        return ResponseEntity.ok(
                ApiResponse.onSuccess(response, MissionSuccessCode.MISSION_FETCHED)
        );
    }

    //미션 성공 누르기(사용자가 미션 요청을 보내면 가게 측에서 approved->completed)
    // 미션 요청 (PENDING)
    @PostMapping("/{userMissionId}/pending")
    public ResponseEntity<ApiResponse<String>> pendingMission(
            @PathVariable Long userMissionId
    ) {

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        "미션 요청 완료",
                        MissionSuccessCode.MISSION_PENDING
                )
        );
    }

    // 가게 승인 (APPROVED)
    @PatchMapping("/{userMissionId}/approved")
    public ResponseEntity<ApiResponse<String>> approveMission(
            @PathVariable Long userMissionId
    ) {

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        "미션 승인 완료",
                        MissionSuccessCode.MISSION_APPROVED
                )
        );
    }

    // 최종 완료 (COMPLETED)
    @PatchMapping("/{userMissionId}/completed")
    public ResponseEntity<ApiResponse<String>> completeMission(
            @PathVariable Long userMissionId
    ) {

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        "미션 완료 처리",
                        MissionSuccessCode.MISSION_COMPLETED
                )
        );
    }

    // 미션 목록 조회 (진행중/진행 완료)
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserMissionResponse>>> getUserMissions(
            @RequestParam(required = false) MissionStatus status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        List<UserMissionResponse> response = List.of(
                new UserMissionResponse(
                        1L,
                        "스타벅스",
                        "아메리카노 3잔 마시기",
                        1000,
                        null,
                        MissionStatus.PENDING
                )
        );

        return ResponseEntity.ok(
                ApiResponse.onSuccess(response, MissionSuccessCode.MISSION_FETCHED)
        );
    }
}