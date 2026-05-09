package org.example.umc10th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.MissionConverter;
import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;

import org.example.umc10th.domain.mission.entity.Region;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.mission.repository.MissionRepository;
import org.example.umc10th.domain.mission.repository.StoreRepository;
import org.example.umc10th.global.apiPayLoad.code.StoreErrorCode;
import org.example.umc10th.global.apiPayLoad.exception.StoreException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Void createMission(
            Long storeId,
            MissionRequest.CreateMission dto
    ){
        //가게 찾기
        Store store=storeRepository.findById(storeId)
                .orElseThrow(()->new StoreException(StoreErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission=MissionConverter.toMission(store, dto);

        //미션 DB 저장
        missionRepository.save(mission);
        return null;
    }
    //가게 내 미션들 조회
    public List<MissionResponse.GetMission> getMissions(
            Long storeId
    ){
        //가게 내 미션들 조회
        List<Mission> missionList=missionRepository.findAllByStore_Id(storeId);

        //미션들 응답 dto로 포장하기
        return missionList.stream()
                .map(MissionConverter::toGetMission)
                .toList();

    }

    //홈 화면(현재 선택된 지역에서 도전이 가능한 미션 목록)
    public List<MissionResponse.GetHomeMission> getHomeMissions(Long regionId,Long userId){

        List<Mission> missions =
                missionRepository.findAvailableMissions(regionId, userId);

        return missions.stream()
                .map(MissionConverter::toGetHomeMission)
                .toList();
    }



}
