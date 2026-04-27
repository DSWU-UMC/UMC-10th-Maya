package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="store_id",nullable=false)
    private Long id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="manager_number",nullable=false)
    private Long mangerNumber;

    @Column(name="address",nullable=false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


}
