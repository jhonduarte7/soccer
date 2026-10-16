package com.venefast.springboot.soccer.app.repositories;

import com.venefast.springboot.soccer.app.models.entities.PaymentConcept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentConceptRepository extends JpaRepository<PaymentConcept, Long> {
    Optional<PaymentConcept> findByConceptName(String conceptName);
}
