package org.example.umc10th.global.security.sevice;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.enums.SocialType;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.example.umc10th.global.apiPayLoad.code.UserErrorCode;
import org.example.umc10th.global.security.exception.UserException;
import org.example.umc10th.global.security.entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;


    public UserDetails loadUserByUidAndSocialType(
            SocialType socialType,
            String username
    ) throws UsernameNotFoundException{
        //DB에서 기존 회원 정보 조회& 인증 객체 생성
        User user=userRepository.findBySocialTypeAndSocialUid(socialType,username)
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));
        return new AuthUser(user);
    }
}
