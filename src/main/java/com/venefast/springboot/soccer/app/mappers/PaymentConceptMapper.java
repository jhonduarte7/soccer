package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.PaymentConceptDto;
import com.venefast.springboot.soccer.app.models.entities.PaymentConcept;
import org.springframework.stereotype.Component;

@Component
public class PaymentConceptMapper {

    public PaymentConceptDto toDto(PaymentConcept entity) {
        if (entity == null) {
            return null;
        }
        return new PaymentConceptDto(
            entity.getId(),
            entity.getConceptName(),
            entity.getExpectedAmount()
        );
    }

    public PaymentConcept toEntity(PaymentConceptDto dto) {
        if (dto == null) {
            return null;
        }
        PaymentConcept concept = new PaymentConcept();
        concept.setId(dto.id());
        concept.setConceptName(dto.conceptName());
        concept.setExpectedAmount(dto.expectedAmount());
        return concept;
    }
}
