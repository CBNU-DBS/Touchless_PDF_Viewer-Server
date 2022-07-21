package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Document extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "doc_id")
    private Long id;

    private String title;

    private String location;

    private LocalDateTime date;

    private String type;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)      // 1:N 관계에서 N쪽에 붙임, FetchType.LAZY = 지연로딩 설정
    @JoinColumn(name = "user_id")           // FK 이름 설정
    private User user;                      // 다른 클래스로 변수를 선언해서 연관관계 설정 가능

    //== 연관관계 편의 메소드==//
    public void setUser(User user){
        this.user = user;
        user.addDocument(this);
    }
}
