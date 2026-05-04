package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th.domain.mission.enums.MissionStatus;
import org.example.umc10th.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_mission_id",nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable=false)
    private MissionStatus status;
    public void approve() {
        this.status = MissionStatus.APPROVED;
    }

    public void complete() {
        this.status = MissionStatus.COMPLETED;
    }

    public void pending() {
        this.status = MissionStatus.PENDING;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;


}
