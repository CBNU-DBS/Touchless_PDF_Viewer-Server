package com.example.DBS.repository;

import com.example.DBS.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor    // final 속성을 가진 변수를 포함하는 생성자 자동생성, DI(생성자 의존성 주입)
public class UserRepository {

    private final EntityManager em;

    /**
     *  유저 저장 (Insert & Update)
     */
    public void save(User user){
        em.persist(user);
    }

    /**
     * 유저 인덱스로 검색
     * @param id    인덱스
     * @return User 클래스
     */
    public User findOne(Long id){
        return em.find(User.class, id);
    }

    /**
     *  모든 유저 정보 가져오기
     * @return 모든 유저 리스트
     */
    public List<User> findAll(){
        // jpql
        return em.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    /**
     * 이메일로 유저 검색
     * @param email 이메일
     * @return  유저 리스트
     */
    public List<User> findByEmail(String email){
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

    }

    /**
     * 이메일, 패스워드로 유저 검색
     * @param email 이메일
     * @param password 비밀번호
     * @return User 클래스
     */
    public User findByEmailAndPassword(String email, String password){
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email and u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    /**
     * 아이디를 통해 유저 검색 후, 패스워드 변경
     * @param id 이메일
     * @param password 비밀번호
     * @return User 클래스
     */
    @Modifying(clearAutomatically = true)
    public void changepasswordById(Long id, String password){
        System.out.println("password = " + password);
        em.createQuery("UPDATE User u SET u.password = :password WHERE u.id = :id")
                .setParameter("password",password)
                .setParameter("id",id)
                .executeUpdate();

        em.clear();
    }
}
