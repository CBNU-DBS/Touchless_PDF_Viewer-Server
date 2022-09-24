package com.example.DBS.repository;

import com.example.DBS.domain.Motion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.management.MonitorInfo;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MotionRepository {
    private final EntityManager em;

    /**
     * 모션 저장
     * @param function
     */
    public void save(Motion function){
        em.persist(function);
    }

    /**
     * 모션 리스트 가져오기
     * @return
     */
    public List<Motion> findAll(){
        return em.createQuery("SELECT m FROM Motion m", Motion.class)
                .getResultList();
    }

    /**
     * 이름으로 모션 가져오기
     * @param name
     * @return
     */
    public Motion findByName(String name){
        return em.createQuery("SELECT m FROM Motion m WHERE m.name = :name", Motion.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
