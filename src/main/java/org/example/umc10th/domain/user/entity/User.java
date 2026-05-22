package org.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10th.common.entity.BaseEntity;
import org.example.umc10th.domain.mission.entity.UserMission;
import org.example.umc10th.domain.review.entity.Review;
import org.example.umc10th.domain.user.enums.Gender;
import org.example.umc10th.domain.user.enums.SocialType;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="social_uid")
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name="social_type")
    private SocialType socialType;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserFood> userFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissions = new ArrayList<>();


}
