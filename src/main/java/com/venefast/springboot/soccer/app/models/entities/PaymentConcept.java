package com.venefast.springboot.soccer.app.models.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "conceptos_pago")
public class PaymentConcept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concepto")
    private Long id;

    @Column(name = "nombre_concepto", nullable = false, length = 120)
    private String conceptName;

    @Column(name = "monto_esperado", nullable = false, precision = 10, scale = 2)
    private BigDecimal expectedAmount;

    public PaymentConcept() {
    }

    public PaymentConcept(Long id, String conceptName, BigDecimal expectedAmount) {
        this.id = id;
        this.conceptName = conceptName;
        this.expectedAmount = expectedAmount;
    }

    public PaymentConcept(String conceptName, BigDecimal expectedAmount) {
        this.conceptName = conceptName;
        this.expectedAmount = expectedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConceptName() {
        return conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(BigDecimal expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentConcept that = (PaymentConcept) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
