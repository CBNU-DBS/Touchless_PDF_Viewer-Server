package com.example.DBS.repository;

import com.example.DBS.domain.Document;
import com.example.DBS.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocumentRepository {
    private final EntityManager em;

    /**
     * 문서 저장
     * @param document 문서
     */
    public void save(Document document){
        em.persist(document);
    }

    /**
     * id로 문서 찾기
     * @param id 문서 아이디
     * @return 문서
     */
    public Document findById(Long id){
        return em.find(Document.class, id);
    }
    /**
     * 해당 User 문서 찾기
     * @param user 유저 정보
     * @return 문서 리스트
     */
    public List<Document> findByUser(User user){
        return em.createQuery("SELECT d FROM Document d WHERE d.user =:user", Document.class)
                .setParameter("user", user)
                .getResultList();
    }

    /**
     * 문서 제목, 유저로 문서 찾기*
     * @param title
     * @param user
     * @return
     */
    public List<Document> findByTitleAndUser(String title, User user){
        return em.createQuery("SELECT d FROM Document d WHERE d.title = :title AND d.user = :user", Document.class)
                .setParameter("title", title)
                .setParameter("user", user)
                .getResultList();
    }

    /**
     * id로 문서 삭제
     * @param id 문서 아이디
     */
    public void deleteById(Long id){
        Document target = findById(id);
        if(target == null){
            throw new IllegalStateException("문서 id값이 유효하지 않습니다.");
        }
        em.remove(target);
    }
}
