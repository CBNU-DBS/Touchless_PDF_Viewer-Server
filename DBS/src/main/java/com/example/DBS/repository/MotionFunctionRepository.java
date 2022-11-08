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

    /**
     * 모션 기능 설정 저장
     * @param motionFunction 모션, 기능 설정
     */
    public void save(MotionFunction motionFunction){
        em.persist(motionFunction);
    }

    /**
     *  사용자에 해당하는 모션 기능 설정 조회
     * @param user 유저 정보
     * @return 모션, 기능 설정
     */
    public List<MotionFunction> findByUser(User user){
        return em.createQuery("SELECT mf FROM MotionFunction mf WHERE mf.user = :user", MotionFunction.class)
                .setParameter("user", user)
                .getResultList();
    }

}
