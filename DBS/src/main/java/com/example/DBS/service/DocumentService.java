package com.example.DBS.service;

import com.example.DBS.DTO.DocumentDTO;
import com.example.DBS.domain.Document;
import com.example.DBS.domain.User;
import com.example.DBS.repository.DocumentRepository;
import com.example.DBS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Document 관련 서비스 로직
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    /**
     * 문서 저장 서비스 로직
     * @param documentDTO 문서 정보
     */
    @Transactional
    public void saveDocument(DocumentDTO documentDTO){

        if(documentDTO == null){
            throw new IllegalStateException("문서가 유효하지 않습니다.");
        }

        User findUser = userRepository.findOne(documentDTO.getUserId());
        if(findUser == null){
            throw new IllegalStateException("사용자가 유효하지 않습니다.");
        }
        List<Document> findDocument = documentRepository.findByTitle(documentDTO.getTitle());
        if(!findDocument.isEmpty()){
            throw new IllegalStateException("문서 제목이 중복됩니다.");
        }

        Document document = new Document();
        document.setTitle(documentDTO.getTitle());
        document.setUser(findUser);
        document.setKey(documentDTO.getKey());

        documentRepository.save(document);
    }

    /**
     * userId로 문서 리스트 조회하는 서비스 로직
     * @param userId 유저 아이디
     * @return 문서 리스트
     */
    public List<DocumentDTO> findDocumentByUser(Long userId){
        List<Document> findDocuments = null;
        List<DocumentDTO> result = new ArrayList<>();
        User findUser = null;

        findUser = userRepository.findOne(userId);
        if(findUser == null){
            throw new IllegalStateException("사용자가 유효하지 않습니다.");
        }

        findDocuments = documentRepository.findByUser(findUser);
        for(Document findDoc : findDocuments){
            DocumentDTO doc = new DocumentDTO(findDoc.getId(), findDoc.getUser().getId(), findDoc.getTitle(), findDoc.getKey(), findDoc.getCreatedDate(), findDoc.getLastModifiedDate());
            result.add(doc);
        }

        return result;
    }
}
