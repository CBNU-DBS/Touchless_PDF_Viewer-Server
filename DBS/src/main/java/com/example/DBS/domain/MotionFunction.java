package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MotionFunction {

    @Id
    @GeneratedValue
    @Column(name = "motion_function_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = MotionConverter.class)
    private Motion motion;

    @Convert(converter = FunctionConverter.class)
    private Function function;

    public MotionFunction(Long id, User user, Motion motion, Function function) {
        this.id = id;
        this.user = user;
        this.motion = motion;
        this.function = function;
    }
}
