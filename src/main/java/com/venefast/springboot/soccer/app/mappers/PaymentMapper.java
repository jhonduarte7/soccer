package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.PaymentDto;
import com.venefast.springboot.soccer.app.models.entities.Payment;
import com.venefast.springboot.soccer.app.models.entities.PaymentConcept;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDto toDto(Payment entity) {
        if (entity == null) {
            return null;
        }
        Long playerId = entity.getPlayer() != null ? entity.getPlayer().getId() : null;
        String playerName = entity.getPlayer() != null ? entity.getPlayer().getFirstName() + " " + entity.getPlayer().getLastName() : null;
        Long conceptId = entity.getPaymentConcept() != null ? entity.getPaymentConcept().getId() : null;
        String conceptName = entity.getPaymentConcept() != null ? entity.getPaymentConcept().getConceptName() : null;
        Long validatorId = entity.getValidatorUser() != null ? entity.getValidatorUser().getId() : null;
        String validatorUsername = entity.getValidatorUser() != null ? entity.getValidatorUser().getUsername() : null;

        return new PaymentDto(
            entity.getId(),
            playerId,
            playerName,
            conceptId,
            conceptName,
            entity.getReportDate(),
            entity.getAmountPaid(),
            entity.getBankReference(),
            entity.getPaymentStatus(),
            validatorId,
            validatorUsername
        );
    }

    public Payment toEntity(PaymentDto dto, Player player, PaymentConcept concept, User validator) {
        if (dto == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(dto.id());
        payment.setPlayer(player);
        payment.setPaymentConcept(concept);
        payment.setReportDate(dto.reportDate());
        payment.setAmountPaid(dto.amountPaid());
        payment.setBankReference(dto.bankReference());
        payment.setPaymentStatus(dto.paymentStatus());
        payment.setValidatorUser(validator);
        return payment;
    }
}
