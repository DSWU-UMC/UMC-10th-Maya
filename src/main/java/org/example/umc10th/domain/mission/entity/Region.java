package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "region")
    private List<Store> stores = new ArrayList<>();
}
