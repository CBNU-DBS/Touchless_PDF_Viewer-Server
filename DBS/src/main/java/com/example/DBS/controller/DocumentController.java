package com.example.DBS.controller;

import com.example.DBS.DTO.DocumentDTO;
import com.example.DBS.domain.BaseResponseBody;
import com.example.DBS.domain.CustomResponseBody;
import com.example.DBS.domain.Document;
import com.example.DBS.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/document")
    public ResponseEntity<BaseResponseBody> saveDocument(@RequestBody DocumentDTO requestBody){
        BaseResponseBody responseBody = new BaseResponseBody("문서 저장 성공");
        try {
            documentService.saveDocument(requestBody);
        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(re.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/documents")
    public ResponseEntity<CustomResponseBody<DocumentDTO>> findDocumentsByUser(@RequestParam(name = "userId") Long userId){
        CustomResponseBody<DocumentDTO> responseBody = new CustomResponseBody<>("문서 검색 성공");
        try{
            List<DocumentDTO> list = new ArrayList<>();
            list = documentService.findDocumentByUser(userId);
            responseBody.setList(list);
        } catch (RuntimeException re){
            responseBody.setResultCode(-1);
            responseBody.setResultMsg(re.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } catch (Exception e){
            responseBody.setResultCode(-2);
            responseBody.setResultMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }

        return ResponseEntity.ok(responseBody);
    }
}
