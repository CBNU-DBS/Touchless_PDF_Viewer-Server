package com.example.DBS.domain;


import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 생성 시간, 수정 시간 컬럼을 위한 Entity
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime lastModifiedDate;
}
