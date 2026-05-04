package org.example.umc10th.domain.mission.dto;

public record PageInfo(
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {}
