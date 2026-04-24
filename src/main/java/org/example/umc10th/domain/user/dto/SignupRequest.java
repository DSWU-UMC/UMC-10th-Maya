package org.example.umc10th.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

public record SignupRequest(
            String name,
            String gender,
            LocalDate birthDate,
            String address,
            List<Long> favoriteFoodIds
    ) {}

