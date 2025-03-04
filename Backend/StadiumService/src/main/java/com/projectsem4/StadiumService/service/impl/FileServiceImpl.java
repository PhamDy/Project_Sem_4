package com.projectsem4.StadiumService.service.impl;

import com.amazonaws.HttpMethod;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.projectsem4.StadiumService.service.FileService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = dateTimeFormatter.format(LocalDate.now());
            String filePath = multipartFile.getOriginalFilename() + "_" + todayDate;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());
            s3Client.putObject(new PutObjectRequest(bucketName, filePath, multipartFile.getInputStream(), metadata));
            return s3Client.getUrl(bucketName, filePath).toString();
        } catch (Exception e) {
            log.error("Error occurred ==> {}", e.getMessage());
            throw new RuntimeException("Error occurred in file upload ==> "+e.getMessage());
        }
    }

    @Override
    public String getFileUrl(String filePath) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, filePath)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            // Trích xuất key từ URL (bỏ phần domain)
            String fileKey = filePath.replace("https://projectsem4.s3.ap-southeast-2.amazonaws.com/", "");

            s3Client.deleteObject(bucketName, fileKey);
            log.info("File deleted successfully from S3: {}", fileKey);
        } catch (Exception e) {
            log.error("Failed to delete file from S3: {}", e.getMessage());
            throw new RuntimeException("Error occurred while deleting file ==> " + e.getMessage());
        }
    }

}
