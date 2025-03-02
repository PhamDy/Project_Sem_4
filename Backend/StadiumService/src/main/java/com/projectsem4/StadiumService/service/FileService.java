package com.projectsem4.StadiumService.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile multipartFile);

    String getFileUrl(String filePath);

    void deleteFile(String filePath);


}
