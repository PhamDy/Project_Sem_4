package com.projectsem4.PaymentService.config.client;

import com.projectsem4.BookingService.client.config.FeignConfig;
import com.projectsem4.common_service.dto.entity.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "BookingServiceClient", url = "${config.url.booking-service-url}", configuration = FeignConfig.class)
public interface BookingServiceClient {

    @GetMapping(value = "/private/api/v1/find-by-id/{id}", consumes = "application/json")
    Booking findById(@PathVariable("id") Long id);

    @GetMapping(value = "/private/api/v1/update-status-by-payment", consumes = "application/json")
    Booking updateStatusByPayment(@RequestParam("status") Integer status, @RequestParam("orderId") Long id);
}
