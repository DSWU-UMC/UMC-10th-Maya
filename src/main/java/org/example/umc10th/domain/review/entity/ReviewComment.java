package org.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name="review_comment")
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_comment_id",nullable = false)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name="content",nullable = false)
    private String content;
}
