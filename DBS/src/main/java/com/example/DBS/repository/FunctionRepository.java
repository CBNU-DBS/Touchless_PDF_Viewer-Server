package com.example.DBS.repository;

import com.example.DBS.domain.Func;
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
     * @param func
     */
    public void save(Func func){
        em.persist(func);
    }

    /**
     * 기능 리스트 가져오기
     * @return
     */
    public List<Func> findAll(){
        return em.createQuery("SELECT f FROM Function f", Func.class)
                .getResultList();
    }

    /**
     * 이름으로 기능 가져오기
     * @param name
     * @return
     */
    public Func findByName(String name){
        return em.createQuery("SELECT f FROM Function f WHERE f.name = :name", Func.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
