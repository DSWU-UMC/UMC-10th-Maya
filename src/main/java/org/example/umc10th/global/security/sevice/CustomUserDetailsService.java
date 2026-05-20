package org.example.umc10th.global.security.sevice;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.entity.User;
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
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException{
        User user=userRepository.findByEmail(username)
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));
        return new AuthUser(user);
    }
}
