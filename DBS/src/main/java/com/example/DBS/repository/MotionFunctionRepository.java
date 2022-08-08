package com.example.DBS.repository;

import com.example.DBS.domain.MotionFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MotionFunctionRepository {

    private final EntityManager em;

    public void save(MotionFunction motionFunction){
        em.persist(motionFunction);
    }
}
