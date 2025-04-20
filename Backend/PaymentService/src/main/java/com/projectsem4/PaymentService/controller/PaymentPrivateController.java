package com.projectsem4.PaymentService.controller;

import com.projectsem4.PaymentService.client.BookingServiceClient;
import com.projectsem4.PaymentService.service.PaymentService;
import com.projectsem4.common_service.dto.SendEmailBookingDTO;
import com.projectsem4.common_service.dto.UserInfor;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.BookingDetail;
import com.projectsem4.common_service.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class PaymentPrivateController {
    private final PaymentService paymentService;
    private final BookingServiceClient bookingServiceClient;

    @PostMapping("/vn-pay/{id}")
    public String createLink(@PathVariable Long id) throws UnsupportedEncodingException {
        String baseUrl = "http://localhost:9007";
        return paymentService.creatPayment(baseUrl,id);
    }

    @GetMapping("/vnPayReturn/{orderId}")
    public ResponseEntity<String> returnPayment(@RequestParam(name = "vnp_ResponseCode") String responseCode,
                                                @PathVariable Long orderId,
                                                HttpServletRequest request) {
        if ("00".equals(responseCode)) {
            // Giao dịch thành công
            paymentService.updateStatusPayment(true, orderId);
            bookingServiceClient.updateStatusByPayment(Constant.OrderStatus.paid, orderId);

            List<BookingDetail> bookingDetails = bookingServiceClient.findByBookingId(orderId);
            String token = JwtUtil.genStringToken(request);
            UserInfor userInfor = JwtUtil.decodeToken(token);
            SendEmailBookingDTO sendEmailBookingDTO = new SendEmailBookingDTO(bookingDetails, userInfor);

            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .location(URI.create("http://localhost:4200"))
                    .build();
        } else {
            // Giao dịch không thành công
            paymentService.updateStatusPayment(false,orderId);
            bookingServiceClient.updateStatusByPayment(Constant.OrderStatus.fail, orderId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Giao dịch không thành công");
        }
    }

}
