package org.example.umc10th.domain.user.repository;

import org.example.umc10th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
