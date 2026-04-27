package org.example.umc10th.domain.mission.controller;

import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.service.MissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/home") //홈 화면: 특정 지역에서 도전 가능한 미션 목록 조호
    public Page<MissionResponse> getChallengableMissions(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return missionService.getHomeMissions(regionId, page, size);
    }
}
