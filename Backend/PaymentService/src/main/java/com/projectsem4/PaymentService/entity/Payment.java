package com.projectsem4.PaymentService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;
    private Long userId;
    private Long total;
    private Long orderId;
    private Long bookingId;
    private LocalDateTime paidAt;
    private Long paymentStatus;
}
