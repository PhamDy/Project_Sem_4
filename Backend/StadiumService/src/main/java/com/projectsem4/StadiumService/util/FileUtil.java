package com.projectsem4.StadiumService.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@UtilityClass
public class FileUtil {

    public String uploadImage(MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.imgur.com/3/image");

        try {
            // Set headers
            httpPost.addHeader("Authorization", "Client-ID 941cc9daaaeec01");

            // Build request body
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addBinaryBody("image", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);

            // Execute the request
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            // Handle response
            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                return extractImageUrl(responseString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle IOException when closing httpClient
            }
        }
        return ""; // Return null if image upload fails
    }

    private String extractImageUrl(String responseJson) {
        // Parse the JSON response to extract the image URL
        try {
            JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
            JsonObject dataObject = jsonObject.getAsJsonObject("data");
            return dataObject.get("link").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
