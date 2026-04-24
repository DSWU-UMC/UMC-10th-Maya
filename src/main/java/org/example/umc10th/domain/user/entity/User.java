package org.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;

    private String email;

    private String password;

    private String phoneNumber;

    private Integer point;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();


}
