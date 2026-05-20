package org.example.umc10th.domain.user.dto;

public class UserRequest {
    //마이페이지
    public record GetInfo(
            Long id
    ){}

    //로그인
    public record LoginRequest(
            String email,
            String password
    ) {
    }
}
