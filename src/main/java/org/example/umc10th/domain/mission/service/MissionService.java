package org.example.umc10th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.MissionConverter;
import org.example.umc10th.domain.mission.dto.MissionRequest;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;


import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.mission.repository.MissionRepository;
import org.example.umc10th.domain.mission.repository.StoreRepository;
import org.example.umc10th.global.apiPayLoad.code.MissionErrorCode;
import org.example.umc10th.global.apiPayLoad.code.StoreErrorCode;
import org.example.umc10th.global.apiPayLoad.exception.MissionException;
import org.example.umc10th.global.apiPayLoad.exception.StoreException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Void createMission(
            Long storeId,
            MissionRequest.CreateMission dto
    ) {

        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 저장
        missionRepository.save(mission);

        return null;
    }

    // 가게 내 미션들 조회
    public MissionResponse.Pagination<MissionResponse.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        // 페이지 정보
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Mission> missionList =
                missionRepository.findMissionByStore_IdOrderByIdDesc(storeId, pageRequest);

        String nextCursor = "-1";

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {

            // 커서 분리
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {

                case "id":

                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    // 미션 조회
                    missionRepository.findByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;

                default:
                    throw new MissionException(
                            MissionErrorCode.QUERY_NOT_VALID
                    );
            }

        } else {

            // 커서 없이 조회
            missionList = missionRepository
                    .findMissionByStore_IdOrderByIdDesc(
                            storeId,
                            pageRequest
                    );
        }

        // 다음 커서 계산
        if (!missionList.getContent().isEmpty()) {

            Mission lastMission =
                    missionList.getContent()
                            .get(missionList.getContent().size() - 1);

            nextCursor =
                    lastMission.getId() + ":" + lastMission.getId();
        }

        // 응답 DTO 변환
        return MissionConverter.toPagenation(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }

    // 홈 화면 미션 조회
    public List<MissionResponse.GetHomeMission> getHomeMissions(
            Long regionId,
            Long userId
    ) {

        List<Mission> missions =
                missionRepository.findAvailableMissions(regionId, userId);

        return missions.stream()
                .map(MissionConverter::toGetHomeMission)
                .toList();
    }
}