package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long mangerNumber;

    private String address;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


}
