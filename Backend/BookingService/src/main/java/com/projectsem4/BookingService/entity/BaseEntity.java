package com.projectsem4.BookingService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    protected String createdBy;
    @CreatedDate
    @Column(name = "CREATED_TIME", updatable = false)
    protected LocalDateTime createdTime;
    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    protected String updatedBy;
    @LastModifiedDate
    @Column(name = "UPDATED_TIME")
    protected LocalDateTime updatedTime;
    @Column(name = "status")
    protected Integer status;
}
