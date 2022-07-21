package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity         // 클래스를 자동으로 테이블로 생성
@Getter @Setter // get,set 메소드 자동 생성
public class User extends BaseTimeEntity{

    @Id                         // PK 표시
    @GeneratedValue             // 자동으로 id 값 설정
    @Column(name = "user_id")  // 테이블 컬럼 이름 설정
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")       // Document 클래스에서 User FK 속성 명시
    private List<Document> documents;

    public void addDocument(Document document){
        this.documents.add(document);
    }
}
