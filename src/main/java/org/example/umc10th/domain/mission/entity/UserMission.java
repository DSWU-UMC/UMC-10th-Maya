package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.user.entity.User;

@Getter
@Entity
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
