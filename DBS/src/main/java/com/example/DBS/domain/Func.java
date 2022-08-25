package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Func {
    @Id
    @GeneratedValue
    @Column(name = "func_id")
    private Long id;

    private String name;

}