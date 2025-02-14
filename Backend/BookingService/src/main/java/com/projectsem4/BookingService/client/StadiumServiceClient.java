package com.projectsem4.BookingService.client;

import com.projectsem4.BookingService.client.config.FeignConfig;
import com.projectsem4.common_service.dto.entity.Accessory;
import com.projectsem4.common_service.dto.entity.Field;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "StadiumServiceClient", url = "${config.url.stadium-service-url}", configuration = FeignConfig.class)
public interface StadiumServiceClient {

    @GetMapping(value = "/private/api/v1/field/{id}", consumes = "application/json")
    Field findFieldById(@PathVariable("id") Long id);

    @GetMapping(value = "/private/api/v1/accessory/{id}", consumes = "application/json")
    Accessory findAccessoryById(@PathVariable("id") Long id);
}
