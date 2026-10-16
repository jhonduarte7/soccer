package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.PaymentMapper;
import com.venefast.springboot.soccer.app.models.dtos.PaymentDto;
import com.venefast.springboot.soccer.app.models.entities.Payment;
import com.venefast.springboot.soccer.app.models.entities.PaymentConcept;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.User;
import com.venefast.springboot.soccer.app.repositories.PaymentConceptRepository;
import com.venefast.springboot.soccer.app.repositories.PaymentRepository;
import com.venefast.springboot.soccer.app.repositories.PlayerRepository;
import com.venefast.springboot.soccer.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PlayerRepository playerRepository;
    private final PaymentConceptRepository paymentConceptRepository;
    private final UserRepository userRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository,
                          PlayerRepository playerRepository,
                          PaymentConceptRepository paymentConceptRepository,
                          UserRepository userRepository,
                          PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.playerRepository = playerRepository;
        this.paymentConceptRepository = paymentConceptRepository;
        this.userRepository = userRepository;
        this.paymentMapper = paymentMapper;
    }

    @Transactional(readOnly = true)
    public List<PaymentDto> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        return paymentMapper.toDto(payment);
    }

    @Transactional(readOnly = true)
    public List<PaymentDto> findByPlayerId(Long playerId) {
        return paymentRepository.findByPlayerId(playerId).stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PaymentDto> findByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status).stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    public PaymentDto create(PaymentDto dto) {
        Player player = playerRepository.findById(dto.playerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + dto.playerId()));
        PaymentConcept concept = paymentConceptRepository.findById(dto.paymentConceptId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment concept not found with id: " + dto.paymentConceptId()));

        User validator = null;
        if (dto.validatorUserId() != null) {
            validator = userRepository.findById(dto.validatorUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Validator user not found with id: " + dto.validatorUserId()));
        }

        Payment payment = paymentMapper.toEntity(dto, player, concept, validator);
        payment.setId(null);
        if (payment.getReportDate() == null) {
            payment.setReportDate(LocalDateTime.now());
        }
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.toDto(saved);
    }

    public PaymentDto update(Long id, PaymentDto dto) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
        Player player = playerRepository.findById(dto.playerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + dto.playerId()));
        PaymentConcept concept = paymentConceptRepository.findById(dto.paymentConceptId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment concept not found with id: " + dto.paymentConceptId()));

        User validator = null;
        if (dto.validatorUserId() != null) {
            validator = userRepository.findById(dto.validatorUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Validator user not found with id: " + dto.validatorUserId()));
        }

        existing.setPlayer(player);
        existing.setPaymentConcept(concept);
        existing.setReportDate(dto.reportDate());
        existing.setAmountPaid(dto.amountPaid());
        existing.setBankReference(dto.bankReference());
        existing.setPaymentStatus(dto.paymentStatus());
        existing.setValidatorUser(validator);

        Payment updated = paymentRepository.save(existing);
        return paymentMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
