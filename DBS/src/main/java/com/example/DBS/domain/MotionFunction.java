package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
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
    @JoinColumn(name = "function_id")
    private Function function;
}
