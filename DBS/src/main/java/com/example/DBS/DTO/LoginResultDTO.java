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
    // 유저 아이디
    private Long id;
    // 유저 이름
    private String name;
    // 유저 이메일
    private String email;
    // 유저 비밀번호
    private String password;
    // 유저 전화번호
    private String phone;
    // 유저가 저장한 문서 리스트
    private List<MotionFunctionDTO> motionFunctionDTOList;

    public LoginResultDTO() {
        motionFunctionDTOList = new ArrayList<>();
    }
}
