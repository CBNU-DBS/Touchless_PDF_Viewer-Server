package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 모션 Entity
 */
@Entity
@Getter @Setter
public class Motion {
    @Id
    @GeneratedValue
    @Column(name = "motion_id")
    private Long id;

    //모션 이름
    private String name;
}
