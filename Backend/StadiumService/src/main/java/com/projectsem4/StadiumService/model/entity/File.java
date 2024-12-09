package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private String fileId;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Basic
    @Column(name = "file_path")
    private String filePath;

    @Basic
    @Column(name = "accessory_id")
    private String accessoryId;

    @Basic
    @Column(name = "area_id")
    private String areaId;

    @Basic
    @Column(name = "category_accessory_id")
    private String categoryAccessoryId;

    @Basic
    @Column(name = "feedback_id")
    private String feedbackId;
}
