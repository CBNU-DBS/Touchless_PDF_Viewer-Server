package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Document extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "doc_id")
    private Long id;
    // 파일 제목
    private String title;
    // s3 url
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)      // 1:N 관계에서 N쪽에 붙임, FetchType.LAZY = 지연로딩 설정
    @JoinColumn(name = "user_id")           // FK 이름 설정
    private User user;                      // 다른 클래스로 변수를 선언해서 연관관계 설정 가능

}
