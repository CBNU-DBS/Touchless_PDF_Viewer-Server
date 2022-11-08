package com.example.DBS.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * 모션 기능 설정 DTO
 */
@Getter
@Setter
public class MotionFunctionDTO {
    private Long userId;
    private String motion;
    private String function;

    public MotionFunctionDTO(Long userId, String motion, String function) {
        this.userId = userId;
        this.motion = motion;
        this.function = function;
    }
}
