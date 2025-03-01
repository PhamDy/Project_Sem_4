package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDb,Long> {
}
