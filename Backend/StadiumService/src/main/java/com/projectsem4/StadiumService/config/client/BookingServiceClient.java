package com.projectsem4.StadiumService.config.client;

import com.projectsem4.StadiumService.config.FeignConfig;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import com.projectsem4.common_service.dto.entity.TimeFrameSchedule;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@FeignClient(name = "BookingServiceClient", url = "${config.url.booking-service-url}", configuration = FeignConfig.class)
public interface BookingServiceClient {

    @PostMapping(value = "/private/api/v1/calender", consumes = "application/json")
    List<TimeFrameSchedule> calenderSchedule(@RequestParam("date") LocalDate date, @RequestParam Long fieldId);
}
