package com.projectsem4.common_service.dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "File")
public class FileDb {

    private Long fileId;

    private Long objectId;

    private String fileName;

    private String filePath;

    private Integer typeFile;

}
