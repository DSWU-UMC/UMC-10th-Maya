package org.example.umc10th.domain.user.converter;


import org.example.umc10th.domain.user.dto.SignupRequest;
import org.example.umc10th.domain.user.dto.UserRequest;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.global.security.dto.OAuthDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    //마이페이지
    public static UserResponse.GetInfo toGetInfo(User user){
        return UserResponse.GetInfo.builder()
                .email(user.getEmail())
                .name(user.getName())
                .point(user.getPoint())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User toUser(OAuthDTO dto) {
        return User.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }
}