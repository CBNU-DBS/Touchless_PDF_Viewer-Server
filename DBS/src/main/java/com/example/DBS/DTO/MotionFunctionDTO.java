package com.example.DBS.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MotionFunctionDTO {
    private long userId;
    private String motion;
    private String function;

    public MotionFunctionDTO(long userId, String motion, String function) {
        this.userId = userId;
        this.motion = motion;
        this.function = function;
    }
}
