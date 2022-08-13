package com.example.DBS.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Function {
    @Id
    @GeneratedValue
    @Column(name = "function_id")
    private Long id;

    private String name;

}
