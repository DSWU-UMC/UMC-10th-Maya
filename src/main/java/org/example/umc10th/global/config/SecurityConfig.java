package org.example.umc10th.global.config;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.global.security.filter.JwtAuthFilter;

import org.example.umc10th.global.security.handler.OAuthSuccessHandler;
import org.example.umc10th.global.security.sevice.CustomOAuthService;
import org.example.umc10th.global.security.sevice.CustomUserDetailsService;
import org.example.umc10th.global.security.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomOAuthService customOAuthService;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",
            "/login/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuthService customOAuthService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // URL 권한 설정
                .authorizeHttpRequests(requests -> requests
                        // 회원가입만 Public API
                        .requestMatchers(allowUris).permitAll()

                        // 그 외 전부 Private API
                        .anyRequest().authenticated()
                )
                //폼 로그인
                .formLogin(AbstractHttpConfigurer::disable)
                //세션
                .sessionManagement(AbstractHttpConfigurer::disable)

                //JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //OAuth
                .oauth2Login(oauth->oauth
                        //인증 엔트리 포인트
                        .authorizationEndpoint(auth->auth
                                .baseUri("/oauth/authorize")
                        )
                        //콜백 주소
                        .redirectionEndpoint(redirect->redirect
                                .baseUri("/oauth/callback/**")
                        )
                        //인증 완료 후 정보 활용
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(customOAuthService)
                        )
                        //성공 시 JWT 토큰 발행할 핸들러
                        .successHandler(oAuthSuccessHandler())
                )



                //예외 상황 핸들러
                .exceptionHandling(exception->exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied(){
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint(){
        return new CustomEntryPoint();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtil,customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }
}
