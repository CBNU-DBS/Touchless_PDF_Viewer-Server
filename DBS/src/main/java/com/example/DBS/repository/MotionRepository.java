package com.example.DBS.repository;

import com.example.DBS.domain.Motion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MotionRepository {
    private final EntityManager em;

    public void save(Motion function){
        em.persist(function);
    }
}
