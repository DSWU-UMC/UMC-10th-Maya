package org.example.umc10th.domain.user.dto;

public record SignupResponse(
        Long userId,
        String name
) {}