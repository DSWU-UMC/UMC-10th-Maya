package org.example.umc10th.domain.user.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th.common.entity.BaseEntity;


@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name="user_food")
public class UserFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) //즉시 로딩
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id")
    private Food food;
}
