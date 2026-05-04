package org.example.umc10th.domain.user.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10th.common.entity.BaseEntity;
import org.example.umc10th.domain.user.enums.FoodName;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="food")
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="food_id", nullable=false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_name", nullable = false)
    private FoodName foodName;
}
