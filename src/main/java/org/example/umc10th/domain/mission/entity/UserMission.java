package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.user.entity.User;

@Getter
@Setter
@Entity
@Table(name="user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_mission_id",nullable=false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable=false)
    private MissionStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
