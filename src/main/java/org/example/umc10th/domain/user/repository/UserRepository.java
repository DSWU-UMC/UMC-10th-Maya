package org.example.umc10th.domain.user.repository;

import org.example.umc10th.domain.user.entity.User;
import org.example.umc10th.domain.user.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findBySocialTypeAndSocialUid(SocialType providerId, String socialUid);
}
