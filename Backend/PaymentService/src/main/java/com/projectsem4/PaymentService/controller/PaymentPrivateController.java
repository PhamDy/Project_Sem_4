package com.projectsem4.PaymentService.controller;

import com.projectsem4.PaymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class PaymentPrivateController {
    private final PaymentService paymentService;

    @PostMapping("/vn-pay/{id}")
    public String createLink(@PathVariable Long id) throws UnsupportedEncodingException {
        String baseUrl = "http://localhost:9007";
        return paymentService.creatPayment(baseUrl,id);
    }

    @GetMapping("/vnPayReturn/{orderId}")
    public ResponseEntity<String> returnPayment(@RequestParam(name = "vnp_ResponseCode") String responseCode,
                                                @PathVariable Long orderId){
        if ("00".equals(responseCode)) {
            // Giao dịch thành công
            paymentService.updateStatusPayment(true, orderId);
//            paymentService.UpdateStatusOrder(true, orderId);

            return ResponseEntity.ok("Giao dịch thành công");
        } else {
            // Giao dịch không thành công
            paymentService.updateStatusPayment(false,orderId);
//            paymentService.UpdateStatusOrder(false,orderId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Giao dịch không thành công");
        }
    }

}
