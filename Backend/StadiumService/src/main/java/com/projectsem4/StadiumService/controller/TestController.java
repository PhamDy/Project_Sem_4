package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/public/api/v1/test")
public class TestController {

    private final FileService fileService;

    @Autowired
    public TestController(FileService fileService) { // ⚠ Sai tên constructor
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        return new ResponseEntity<>(fileService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/getUrl")
    public ResponseEntity<?> getUrl(String path) {
        return new ResponseEntity<>(fileService.getFileUrl(path), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(String path) {
        fileService.deleteFile(path);
        return new ResponseEntity<>(null , HttpStatus.OK);
    }

}
