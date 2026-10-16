package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pagos")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_concepto", nullable = false)
    private PaymentConcept paymentConcept;

    @Column(name = "fecha_reporte", nullable = false)
    private LocalDateTime reportDate;

    @Column(name = "monto_pagado", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @Column(name = "referencia_bancaria", nullable = false, length = 100)
    private String bankReference;

    @Column(name = "estado_pago", nullable = false, length = 40)
    private String paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_validador")
    private User validatorUser;

    public Payment() {
    }

    public Payment(Long id, Player player, PaymentConcept paymentConcept, LocalDateTime reportDate, BigDecimal amountPaid, String bankReference, String paymentStatus, User validatorUser) {
        this.id = id;
        this.player = player;
        this.paymentConcept = paymentConcept;
        this.reportDate = reportDate;
        this.amountPaid = amountPaid;
        this.bankReference = bankReference;
        this.paymentStatus = paymentStatus;
        this.validatorUser = validatorUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PaymentConcept getPaymentConcept() {
        return paymentConcept;
    }

    public void setPaymentConcept(PaymentConcept paymentConcept) {
        this.paymentConcept = paymentConcept;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getBankReference() {
        return bankReference;
    }

    public void setBankReference(String bankReference) {
        this.bankReference = bankReference;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getValidatorUser() {
        return validatorUser;
    }

    public void setValidatorUser(User validatorUser) {
        this.validatorUser = validatorUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
