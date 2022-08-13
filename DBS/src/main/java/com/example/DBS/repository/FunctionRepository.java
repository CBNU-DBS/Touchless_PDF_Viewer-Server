package com.example.DBS.repository;

import com.example.DBS.domain.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FunctionRepository {
    private final EntityManager em;

    /**
     * 기능 저장
     * @param function
     */
    public void save(Function function){
        em.persist(function);
    }

    /**
     * 기능 리스트 가져오기
     * @return
     */
    public List<Function> findAll(){
        return em.createQuery("SELECT f FROM Function f", Function.class)
                .getResultList();
    }

    /**
     * 이름으로 기능 가져오기
     * @param name
     * @return
     */
    public Function findByName(String name){
        return em.createQuery("SELECT f FROM Function f WHERE f.name = :name", Function.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
