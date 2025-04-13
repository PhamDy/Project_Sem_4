package com.projectsem4.BookingService.client;

import com.projectsem4.BookingService.client.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "PaymentServiceClient", url = "${config.url.payment-service-url}", configuration = FeignConfig.class)
public interface PaymentServiceClient {

    @PostMapping(value = "/private/api/v1/vn-pay/{id}", consumes = "application/json")
    String linkThanhToan(@PathVariable Long id);

}
