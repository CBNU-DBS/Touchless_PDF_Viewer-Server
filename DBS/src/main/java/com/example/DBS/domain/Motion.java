package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Motion {
    @Id
    @GeneratedValue
    @Column(name = "motion_id")
    private Long id;

    private String name;
}
