package org.example.umc10th.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    private Long id;


}
