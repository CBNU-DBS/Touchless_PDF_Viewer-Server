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
     * @param document
     */
    public void save(Document document){
        em.persist(document);
    }

    /**
     * id로 문서 찾기
     * @param id
     * @return
     */
    public Document findById(Long id){
        return em.find(Document.class, id);
    }
    /**
     * 해당 User 문서 찾기
     * @param user
     * @return
     */
    public List<Document> findByUser(User user){
        return em.createQuery("SELECT d FROM Document d WHERE d.user =:user", Document.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Document> findByTitle(String title){
        return em.createQuery("SELECT d FROM Document d WHERE d.title = :title", Document.class)
                .setParameter("title", title)
                .getResultList();
    }

    /**
     * id로 문서 삭제
     * @param id
     */
    public void deleteById(Long id){
        Document target = findById(id);
        if(target == null){
            throw new IllegalStateException("문서 id값이 유효하지 않습니다.");
        }
        em.remove(target);
    }
}
