package org.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.umc10th.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long storeId;

    private String content;

    @Column(precision = 2, scale = 1)
    private BigDecimal score;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
