package org.example.umc10th.domain.user.dto;

import org.example.umc10th.domain.user.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public record SignupRequest(

            String email,
            String password,


            String name,
            Gender gender,
            String address,
            String phoneNumber,
            List<Long> favoriteFoodIds

    ) {}

