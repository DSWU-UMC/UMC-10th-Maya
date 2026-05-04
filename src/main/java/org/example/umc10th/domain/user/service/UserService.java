package org.example.umc10th.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th.domain.user.converter.UserConverter;
import org.example.umc10th.domain.user.dto.MyPageResponse;
import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public MyPageResponse getMyPage(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(); // 단순 처리 (에러 코드는 Controller에서 처리)

        return userConverter.toMyPageDto(user);
    }

    public User getUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        return user;
    }


}
