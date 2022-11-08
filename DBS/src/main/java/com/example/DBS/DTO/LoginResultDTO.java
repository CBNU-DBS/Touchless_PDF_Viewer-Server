package com.example.DBS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 로그인 결과 DTO
 */
@Getter
@Setter
public class LoginResultDTO {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private List<MotionFunctionDTO> motionFunctionDTOList;

    public LoginResultDTO() {
        motionFunctionDTOList = new ArrayList<>();
    }
}
