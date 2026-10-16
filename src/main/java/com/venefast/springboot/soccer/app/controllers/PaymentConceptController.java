package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.PaymentConceptDto;
import com.venefast.springboot.soccer.app.services.PaymentConceptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-concepts")
@CrossOrigin(origins = "*")
public class PaymentConceptController {

    private final PaymentConceptService paymentConceptService;

    public PaymentConceptController(PaymentConceptService paymentConceptService) {
        this.paymentConceptService = paymentConceptService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentConceptDto>> getAll() {
        return ResponseEntity.ok(paymentConceptService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentConceptDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentConceptService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentConceptDto> create(@Valid @RequestBody PaymentConceptDto dto) {
        PaymentConceptDto created = paymentConceptService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentConceptDto> update(@PathVariable Long id, @Valid @RequestBody PaymentConceptDto dto) {
        return ResponseEntity.ok(paymentConceptService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentConceptService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
