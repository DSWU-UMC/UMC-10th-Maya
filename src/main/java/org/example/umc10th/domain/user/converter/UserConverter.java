package org.example.umc10th.domain.user.converter;

import org.example.umc10th.domain.user.dto.MyPageResponse;
import org.example.umc10th.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public MyPageResponse toMyPageDto(User user) {
        return new MyPageResponse(
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPoint()
        );
    }
}