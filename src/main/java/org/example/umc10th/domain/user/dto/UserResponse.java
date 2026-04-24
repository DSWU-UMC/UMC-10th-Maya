package org.example.umc10th.domain.user.dto;

import lombok.Builder;

public class UserResponse {
    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
