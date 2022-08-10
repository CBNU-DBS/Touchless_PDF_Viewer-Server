package com.example.DBS.repository;

import com.example.DBS.domain.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FunctionRepository {
    private final EntityManager em;

    public void save(Function function){
        em.persist(function);
    }
}
