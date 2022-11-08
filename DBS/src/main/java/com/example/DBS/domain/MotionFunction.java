package com.example.DBS.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 모션, 기능, 사용자 매핑 Entity
 */
@Entity
@Getter @Setter
public class MotionFunction {
    @Id
    @GeneratedValue
    @Column(name = "motion_function_id")
    private Long id;

    // 사용자
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    // 모션
    @ManyToOne
    @JoinColumn(name = "motion_id")
    private Motion motion;

    // 기능
    @ManyToOne
    @JoinColumn(name = "func_id")
    private Func func;

}
