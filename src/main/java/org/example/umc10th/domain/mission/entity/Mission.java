package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate deadline;

    private String conditional;

    private Integer point;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


}
