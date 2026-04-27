package org.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "score", nullable = false, precision = 2, scale = 1)
    private BigDecimal score;

    private LocalDateTime createdAt = LocalDateTime.now(); // 기본값 설정

    @OneToMany(mappedBy = "review")
    private List<ReviewComment> comments = new ArrayList<>();

    // 4개의 필드를 받는 생성자 추가
    public Review(Store store, User user, String content, BigDecimal score) {
        this.store = store;
        this.user = user;
        this.content = content;
        this.score = score;
        this.createdAt = LocalDateTime.now(); // 생성자에서 createdAt 설정
    }
}