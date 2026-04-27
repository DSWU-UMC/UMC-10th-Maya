package org.example.umc10th.domain.user.dto;

public record MyPageResponse(
        String name,
        String email,
        String phoneNumber,
        Integer point
) {}
