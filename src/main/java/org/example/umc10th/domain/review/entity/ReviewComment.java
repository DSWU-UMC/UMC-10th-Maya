package org.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th.common.entity.BaseEntity;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="review_comment")
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;


    @Column(name="content",nullable = false)
    private String content;
}
