package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;



@Getter
@Entity
@Table(name="region")
public class Region {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="region_id",nullable=false)
    private Long id;

    @Column(name="name",nullable=false)
    private String name;

    @OneToMany(mappedBy = "region")
    private List<Store> stores = new ArrayList<>();
}
