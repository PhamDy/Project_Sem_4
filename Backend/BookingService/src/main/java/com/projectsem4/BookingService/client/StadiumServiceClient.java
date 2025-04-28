package com.projectsem4.BookingService.client;

import com.projectsem4.BookingService.client.config.FeignConfig;
import com.projectsem4.common_service.dto.entity.Accessory;
import com.projectsem4.common_service.dto.entity.AreaCreateRequest;
import com.projectsem4.common_service.dto.entity.FieldType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "StadiumServiceClient", url = "${config.url.stadium-service-url}", configuration = FeignConfig.class)
public interface StadiumServiceClient {

    @GetMapping(value = "/private/api/v1/field/{id}", consumes = "application/json")
    ResponseEntity<FieldType> findFieldById(@PathVariable("id") Long id);

    @GetMapping(value = "/private/api/v1/area/{id}", consumes = "application/json")
    ResponseEntity<AreaCreateRequest> findAreaById(@PathVariable("id") Long id);

    @GetMapping(value = "/private/api/v1/accessory/{id}", consumes = "application/json")
    Accessory findAccessoryById(@PathVariable("id") Long id);
}
