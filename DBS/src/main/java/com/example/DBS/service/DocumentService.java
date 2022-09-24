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

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

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
