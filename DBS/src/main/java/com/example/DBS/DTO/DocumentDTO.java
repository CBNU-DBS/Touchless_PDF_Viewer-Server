package com.example.DBS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 문서 DTO
 */
@Getter @Setter
public class DocumentDTO {
    //문서 번호
    private Long id;
    // 유저 ID
    private Long userId;
    // 문서 제목
    private String title;
    // 문서 고유 키값
    private String key;
    // 생성일
    private LocalDateTime createdDate;
    // 최종 수정일
    private LocalDateTime lastModifiedDate;

    public DocumentDTO(Long id, Long userId, String title, String key, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.key = key;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
