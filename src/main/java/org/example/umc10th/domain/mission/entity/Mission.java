package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name="mission")
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mission_id",nullable=false)
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


}
