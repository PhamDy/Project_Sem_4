package com.projectsem4.PaymentService.service;

import com.projectsem4.PaymentService.config.ConfigVnPay;
import com.projectsem4.PaymentService.client.BookingServiceClient;
import com.projectsem4.PaymentService.entity.Payment;
import com.projectsem4.PaymentService.repository.PaymentRepository;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingServiceClient bookingServiceClient;

    public String creatPayment( String urlReturn, Long orderId) throws UnsupportedEncodingException {
        Booking booking = bookingServiceClient.findById(orderId);
//        Order order = restTemplate.getForObject("http://orderService/api/v1/order/"+ orderId, Order.class);
//        Product product = restTemplate.getForObject("http://localhost:8083/api/v1/product/"+ order.getProductId(), Product.class);

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = ConfigVnPay.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = ConfigVnPay.vnp_TmnCode;
        String orderType = "other";
        String bankCode = "NCB";


//        String orderInfo = product.getName();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(booking.getTotalPrice() != null ? booking.getTotalPrice()*100 : 0));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "orderInfo");
        vnp_Params.put("vnp_OrderType", orderType);
        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);
        urlReturn += ConfigVnPay.urlReturn;
        vnp_Params.put("vnp_ReturnUrl", urlReturn + "/"+orderId);

        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigVnPay.hmacSHA512(ConfigVnPay.secretKey,
                hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigVnPay.vnp_PayUrl + "?" + queryUrl;
        savePayment(orderId);
        return paymentUrl;
    }

//    public Page<Payment> getByUsername(Pageable pageable, Long userId){
//        return paymentRepository.findByUserId(pageable,userId);
//    }

    public void savePayment(Long orderId){
        Booking booking = bookingServiceClient.findById(orderId);
        Payment payment = new Payment();
        payment.setUserId(booking.getUserId());
        payment.setPaidAt(now());
        payment.setTotal(booking.getTotalPrice());
        payment.setOrderId(orderId);
        payment.setStatus(Constant.PaymentStatus.pending);
        paymentRepository.save(payment);
    }
    public void updateStatusPayment(Boolean isDone, Long orderId){
        Optional<Payment> payment = paymentRepository.findByOrderId(orderId);
        if(isDone){
            payment.get().setStatus(Constant.PaymentStatus.completed);
        }
        else {
            payment.get().setStatus(Constant.PaymentStatus.fail);
        }
        paymentRepository.save(payment.get());
    }



//    public void UpdateStatusOrder(Boolean a, Long orderId){
//        RequestUpdateStatusOrder requestUpdateStatusOrder = new RequestUpdateStatusOrder();
//        requestUpdateStatusOrder.setStatus(a);
//        requestUpdateStatusOrder.setOrderId(orderId);
//        // 0 : complete, 1: refund, 2: fail
//        log.info("Before publishing a OrderCreatedEvent");
//
//        kafkaProducer.sendMessageStatusOrder(requestUpdateStatusOrder);
//
//        log.info("******* Returning");
//
//    }

    public Payment getById(Long id){
        return paymentRepository.findById(id).orElse(null);
    }
    public Payment getByOrderId(Long id){
        return paymentRepository.findByOrderId(id).get();
    }
}