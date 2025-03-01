package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileDb,Long> {

    List<FileDb> findByObjectIdAndTypeFile(Long objectId, Integer type);

    List<FileDb> findByObjectIdInAndTypeFile(List<Long> objectIds, Integer typeFile);
}
