package org.example.umc10th.domain.mission.controller;

import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.service.MissionService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/home") //홈 화면: 특정 지역에서 도전 가능한 미션 목록 조회
    public Page<MissionResponse> getChallengableMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return missionService.getHomeMissions(regionId, page, size);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createMission(
            @RequestBody MissionRequest.CreateMission request
    ) {
        missionService.createMission(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(null, MissionSuccessCode.CREATED));
    }
}
