package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
