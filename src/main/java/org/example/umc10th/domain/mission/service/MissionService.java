package org.example.umc10th.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.mission.converter.MissionConverter;
import org.example.umc10th.domain.mission.dto.MissionResponse;
import org.example.umc10th.domain.mission.entity.Mission;
import org.example.umc10th.domain.mission.entity.Region;
import org.example.umc10th.domain.mission.repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionConverter missionConverter;

    public Page<MissionResponse> getHomeMissions(Long regionId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return missionRepository.findChallengableMissions(regionId, pageable)
                .map(missionConverter::toDto);
    }
}
