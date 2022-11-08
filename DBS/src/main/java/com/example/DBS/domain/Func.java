package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 기능 Entity
 */
@Entity
@Getter @Setter
public class Func {
    @Id
    @GeneratedValue
    @Column(name = "func_id")
    private Long id;

    // 기능 이름
    private String name;

}
