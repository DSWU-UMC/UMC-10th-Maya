package org.example.umc10th.domain.mission.controller;

import lombok.RequiredArgsConstructor;

import org.example.umc10th.domain.mission.controller.docs.MissionControllerDocs;
import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.service.MissionService;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.example.umc10th.global.apiPayLoad.code.MissionSuccessCode;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    //가게 미션 생성
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionRequest.CreateMission dto
    )
    {
        BaseSuccessCode code=MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code,missionService.createMission(storeId,dto));
    }

    //가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResponse.Pagination<MissionResponse.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code=MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code,missionService.getMissions(storeId,pageSize,cursor,query));
    }




}
