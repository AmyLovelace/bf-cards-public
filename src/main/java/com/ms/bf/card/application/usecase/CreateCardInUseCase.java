package com.ms.bf.card.application.usecase;

import com.ms.bf.card.adapter.kafka.KafkaProducerPort;
import com.ms.bf.card.application.port.in.CreateIn;
import com.ms.bf.card.domain.Card;
import com.ms.bf.card.config.exception.GenericException;
import com.ms.bf.card.config.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CreateCardInUseCase implements CreateIn {

    private final KafkaProducerPort kafkaProducerPort;

    @Override
    public Integer create(Card card) throws GenericException {
        try {
            return kafkaProducerPort.sendMessage(card);
        } catch (MessagingException e) {
            log.error("Error al generar el mensaje: ", e);
            throw new GenericException(ErrorCode.CARD_INVALID_REQUEST, "Error al generar el mensaje");
        }
    }
}
