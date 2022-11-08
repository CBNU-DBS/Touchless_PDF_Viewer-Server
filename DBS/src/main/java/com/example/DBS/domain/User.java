package com.example.DBS.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 Entity
 */
@Entity         // 클래스를 자동으로 테이블로 생성
@Getter @Setter // get,set 메소드 자동 생성
public class User extends BaseTimeEntity{

    @Id                         // PK 표시
    @GeneratedValue             // 자동으로 id 값 설정
    @Column(name = "user_id")  // 테이블 컬럼 이름 설정
    private Long id;

    // 이름
    private String name;
    // 이메일
    private String email;
    // 비밀번호
    private String password;
    // 전화번호
    private String phone;
    // 문서
    @OneToMany(mappedBy = "user")       // Document 클래스에서 User FK 속성 명시
    @JsonManagedReference
    private List<Document> documents;
    // 모션 기능 설정
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<MotionFunction> motionFunctionList;

    public User() {
        documents = new ArrayList<>();
        motionFunctionList = new ArrayList<>();
    }
}
