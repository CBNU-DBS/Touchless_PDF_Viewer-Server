package com.example.DBS.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class DocumentDTO {
    private Long id;
    private Long userId;
    private String title;
    private String location;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public DocumentDTO(Long id, Long userId, String title, String location, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
