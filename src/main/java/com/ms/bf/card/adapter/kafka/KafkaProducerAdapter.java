package com.ms.bf.card.adapter.kafka;
import com.ms.bf.card.config.property.KafkaProperty;
import com.ms.bf.card.domain.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerAdapter implements KafkaProducerPort{

    private final KafkaTemplate<String,Card> kafkaTemplate;

    private final KafkaProperty kafkaProperty;


    public KafkaProducerAdapter(KafkaTemplate<String, Card> kafkaTemplate, KafkaProperty kafkaProperty) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperty = kafkaProperty;
    }

    @Override
    public Integer SendMessage(Card card) {
        log.info("Message sent -> {} ", card.toString());
        Message<Card> message = MessageBuilder.withPayload(card).setHeader(KafkaHeaders.TOPIC, kafkaProperty.getTopicName()).build();
        kafkaTemplate.send(message);
        return 0;
        //ATRAPAR EXCEPCION
    }

}

