package com.projectsem4.StadiumService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "File")
public class FileDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Basic
    @Column(name = "object_id")
    private Long objectId;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Basic
    @Column(name = "file_path")
    private String filePath;

    @Basic
    @Column(name = "type_file")
    private Integer typeFile;

}
