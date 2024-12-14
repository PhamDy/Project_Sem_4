package com.projectsem4.common_service.dto.entity;


import java.time.LocalDateTime;

public abstract class BaseEntity{
    private static final long serialVersionUID = 1L;
    protected LocalDateTime createdTime;
    protected String updatedBy;
    protected LocalDateTime updatedTime;
    protected String tenant;
    protected Integer status;
}
