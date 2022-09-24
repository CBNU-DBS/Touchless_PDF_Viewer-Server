package com.example.DBS.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_motion_func_uq", columnNames = {"user_id", "motion_id", "func_id"}
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
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "motion_id")
    private Motion motion;

    @ManyToOne
    @JoinColumn(name = "func_id")
    private Func func;

}
