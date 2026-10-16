package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPlayerId(Long playerId);
    List<Payment> findByPaymentStatus(String paymentStatus);
}
