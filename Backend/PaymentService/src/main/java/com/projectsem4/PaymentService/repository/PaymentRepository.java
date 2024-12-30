package com.projectsem4.PaymentService.repository;

import com.projectsem4.PaymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBookingId(long id);
    Optional<Payment> findByOrderId(long id);
}
