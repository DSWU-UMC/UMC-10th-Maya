package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="mission")
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="deadline",nullable=false)
    private LocalDate deadline;

    @Column(name="conditional",nullable=false)
    private String conditional;

    @Column(name="point",nullable=false)
    private Integer point;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


    @OneToMany(mappedBy = "mission")
    private List<UserMission> userMissions = new ArrayList<>();


}
