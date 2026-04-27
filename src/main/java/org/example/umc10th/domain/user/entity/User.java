package org.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable=false)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", nullable=false)
    @Builder.Default
    private Gender gender=Gender.NONE;

    @Column(name="address", nullable=false)
    private String address;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="phone_number", nullable=false)
    private String phoneNumber;

    @Column(name="point", nullable=false)
    private Integer point;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();


}
