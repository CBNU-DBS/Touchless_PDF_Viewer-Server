package com.example.DBS.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * 모션 기능 설정 DTO
 */
@Getter
@Setter
public class MotionFunctionDTO {
    // 유저 아이디
    private Long userId;
    // 모션
    private String motion;
    // 기능
    private String function;

    public MotionFunctionDTO(Long userId, String motion, String function) {
        this.userId = userId;
        this.motion = motion;
        this.function = function;
    }
}
