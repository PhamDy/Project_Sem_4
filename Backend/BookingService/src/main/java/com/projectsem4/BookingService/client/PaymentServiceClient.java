package com.projectsem4.BookingService.client;

import com.projectsem4.BookingService.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "PaymentServiceClient", url = "${config.url.payment-service-url}", configuration = FeignConfig.class)
public interface PaymentServiceClient {

    @GetMapping(value = "/public/api/v1", consumes = "application/json")
    String linkThanhToan();

}
