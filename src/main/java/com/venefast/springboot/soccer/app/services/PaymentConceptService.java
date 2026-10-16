package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.PaymentConceptMapper;
import com.venefast.springboot.soccer.app.models.dtos.PaymentConceptDto;
import com.venefast.springboot.soccer.app.models.entities.PaymentConcept;
import com.venefast.springboot.soccer.app.repositories.PaymentConceptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentConceptService {

    private final PaymentConceptRepository paymentConceptRepository;
    private final PaymentConceptMapper paymentConceptMapper;

    public PaymentConceptService(PaymentConceptRepository paymentConceptRepository,
                                 PaymentConceptMapper paymentConceptMapper) {
        this.paymentConceptRepository = paymentConceptRepository;
        this.paymentConceptMapper = paymentConceptMapper;
    }

    @Transactional(readOnly = true)
    public List<PaymentConceptDto> findAll() {
        return paymentConceptRepository.findAll().stream()
                .map(paymentConceptMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PaymentConceptDto findById(Long id) {
        PaymentConcept concept = paymentConceptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment concept not found with id: " + id));
        return paymentConceptMapper.toDto(concept);
    }

    public PaymentConceptDto create(PaymentConceptDto dto) {
        PaymentConcept concept = paymentConceptMapper.toEntity(dto);
        concept.setId(null);
        PaymentConcept saved = paymentConceptRepository.save(concept);
        return paymentConceptMapper.toDto(saved);
    }

    public PaymentConceptDto update(Long id, PaymentConceptDto dto) {
        PaymentConcept existing = paymentConceptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment concept not found with id: " + id));

        existing.setConceptName(dto.conceptName());
        existing.setExpectedAmount(dto.expectedAmount());

        PaymentConcept updated = paymentConceptRepository.save(existing);
        return paymentConceptMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!paymentConceptRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment concept not found with id: " + id);
        }
        paymentConceptRepository.deleteById(id);
    }
}
