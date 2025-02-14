package com.projectsem4.StadiumService.config.client;

import com.projectsem4.StadiumService.config.FeignConfig;

import com.projectsem4.common_service.dto.entity.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@FeignClient(name = "BookingServiceClient", url = "${config.url.booking-service-url}", configuration = FeignConfig.class)
public interface BookingServiceClient {

    @PostMapping(value = "/private/api/v1/check-valid-time", consumes = "application/json")
    List<Price> findTimeAvailable(@RequestParam("date") LocalDate date, @RequestBody List<Price> price);
}
