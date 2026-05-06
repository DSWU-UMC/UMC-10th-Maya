package org.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th.common.entity.BaseEntity;
import org.example.umc10th.domain.mission.entity.Store;
import org.example.umc10th.domain.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewComment> comments = new ArrayList<>();


}