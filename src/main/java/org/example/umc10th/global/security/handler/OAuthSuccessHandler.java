package org.example.umc10th.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.converter.AuthConverter;
import org.example.umc10th.domain.user.dto.UserResponse;
import org.example.umc10th.global.apiPayLoad.ApiResponse;
import org.example.umc10th.global.apiPayLoad.code.BaseSuccessCode;
import org.example.umc10th.global.apiPayLoad.code.UserSuccessCode;
import org.example.umc10th.global.security.entity.AuthUser;
import org.example.umc10th.global.security.entity.OAuthUser;
import org.example.umc10th.global.security.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 사전 작업: Response 매핑할 ObjectMapper 선언
        ObjectMapper objectMapper = new ObjectMapper();
        BaseSuccessCode code = UserSuccessCode.OK;

        // Content-Type, Status 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // 인증 객체 컨테이너에서 OAuth 인증 객체 가져오기
        OAuthUser user = (OAuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 토큰 제작을 위해 OAuth 인증 객체에서 Member 추출 -> AuthMember 제작
        String accessToken = jwtUtil.createAccessToken(new AuthUser(user.getUser()));

        // 응답 통일 객체 래핑
        ApiResponse<UserResponse.LoginResponse> responseBody = ApiResponse.onSuccess(
                code,
                AuthConverter.toLoginResponse(accessToken)
        );

        // 응답 출력
        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}