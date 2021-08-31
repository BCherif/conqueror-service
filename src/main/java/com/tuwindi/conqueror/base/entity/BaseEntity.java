package com.tuwindi.conqueror.base.entity;

import com.tuwindi.conqueror.utils.Utils;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity implements CEntity {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(name = "uid", nullable = false, unique = true)
    protected String uid;

    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    @CreatedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME")
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at", columnDefinition = "DATETIME")
    protected LocalDateTime lastModifiedAt;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "DATE")
    protected LocalDate createdDate;

    @CreatedDate
    @Column(name = "created_time", columnDefinition = "TIME")
    protected LocalTime createdTime;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "DATE")
    protected LocalDate lastModifiedDate;

    @LastModifiedDate
    @Column(name = "last_modified_time", columnDefinition = "TIME")
    protected LocalTime lastModifiedTime;

    @PrePersist
    public void prePersist() {
        uid = Utils.uid();
    }
}
