package org.example.umc10th.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.UserMissionConverter;
import org.example.umc10th.domain.mission.dto.PageInfo;
import org.example.umc10th.domain.mission.dto.UserMissionListResponse;
import org.example.umc10th.domain.mission.dto.UserMissionRequest;
import org.example.umc10th.domain.mission.dto.UserMissionResponse;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.mission.service.UserMissionService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/user-missions")
@RequiredArgsConstructor
public class UserMissionController {

    private final UserMissionService userMissionService;
    private final UserMissionConverter userMissionConverter;

    // 미션 조회 (진행중 / 완료)
    @PostMapping
    public ResponseEntity<ApiResponse<UserMissionListResponse>> getUserMissions(
            @RequestBody UserMissionRequest request
    ) {

        Pageable pageable = PageRequest.of(
                request.page(),
                request.size()
        );

        Page<UserMission> missions =
                userMissionService.getUserMissions(
                        request.userId(),
                        request.completed(),
                        pageable
                );

        List<UserMissionResponse> response = missions.stream()
                .map(userMissionConverter::toDto)
                .toList();

        return ResponseEntity.ok(
                ApiResponse.onSuccess(
                        new UserMissionListResponse(
                                response,
                                new PageInfo(
                                        missions.getNumber(),
                                        missions.getSize(),
                                        missions.getTotalElements(),
                                        missions.getTotalPages()
                                )
                        ),
                        MissionSuccessCode.OK
                )
        );
    }
}