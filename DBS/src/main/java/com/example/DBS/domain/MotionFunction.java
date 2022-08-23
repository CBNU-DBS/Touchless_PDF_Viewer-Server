package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_motion_uq", columnNames = {"user_id", "motion_id"}
                ),
                @UniqueConstraint(
                        name = "user_function_uq", columnNames = {"user_id", "func_id"}
                )
        }
)
public class MotionFunction {
    @Id
    @GeneratedValue
    @Column(name = "motion_function_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "motion_id")
    private Motion motion;

    @ManyToOne
    @JoinColumn(name = "func_id")
    private Func func;

}
