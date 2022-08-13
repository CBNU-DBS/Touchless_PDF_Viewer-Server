package com.example.DBS.repository;

import com.example.DBS.domain.MotionFunction;
import com.example.DBS.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MotionFunctionRepository {
    private final EntityManager em;

    public void save(MotionFunction motionFunction){
        em.persist(motionFunction);
    }

    public List<MotionFunction> findByUser(User user){
        return em.createQuery("SELECT mf FROM MotionFunction mf WHERE mf.user = :user", MotionFunction.class)
                .setParameter("user", user)
                .getResultList();
    }

}
