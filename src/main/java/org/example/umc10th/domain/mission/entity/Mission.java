package org.example.umc10th.domain.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Mission {
    @Id
    private Long id;
}
