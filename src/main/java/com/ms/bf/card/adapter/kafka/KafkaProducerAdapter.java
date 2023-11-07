package com.ms.bf.card.adapter.kafka;

import com.ms.bf.card.config.property.KafkaProperty;
import com.ms.bf.card.domain.Account;
import com.ms.bf.card.config.exception.GenericException;
import com.ms.bf.card.config.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerAdapter implements KafkaProducerPort{

    private final KafkaTemplate<String, Account> kafkaTemplate;

    private final KafkaProperty kafkaProperty;


    public KafkaProducerAdapter(KafkaTemplate<String, Account> kafkaTemplate, KafkaProperty kafkaProperty) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperty = kafkaProperty;
    }

    @Override
    public Integer sendMessage(Account card) throws GenericException {
        try {
            log.info("Message sent -> {} ", card.toString());
            Message<Account> message = MessageBuilder.withPayload(card).setHeader(KafkaHeaders.TOPIC, kafkaProperty.getTopicName()).build();
            kafkaTemplate.send(message);
            log.info("Sent message value: {}", card);
            return 0;
        } catch (MessagingException e) {
            log.error("Error al generar el mensaje: ", e);
            throw new GenericException(ErrorCode.CARD_INVALID_REQUEST, "Error al generar el mensaje");
        }
    }
}
