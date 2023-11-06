package com.ms.bf.card.adapter;

import com.ms.bf.card.adapter.kafka.KafkaProducerAdapter;
import com.ms.bf.card.config.exception.GenericException;
import com.ms.bf.card.config.property.KafkaProperty;
import com.ms.bf.card.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class KafkaProducerAdapterTest {

    private KafkaTemplate<String, Card> kafkaTemplate;
    private KafkaProperty kafkaProperty;
    private KafkaProducerAdapter kafkaProducerAdapter;

    @BeforeEach
    public void setUp() {
        kafkaTemplate = mock(KafkaTemplate.class);
        kafkaProperty = mock(KafkaProperty.class);
        kafkaProducerAdapter = new KafkaProducerAdapter(kafkaTemplate, kafkaProperty);
    }

    @Test
    public void testSendMessage() {
        Card card = Card.builder()
                .accountNumber("12345678-9")
                .age(20)
                .build();

        when(kafkaProperty.getTopicName()).thenReturn("testTopic");

        Integer result = kafkaProducerAdapter.sendMessage(card);

        verify(kafkaTemplate, times(1)).send(any(Message.class));
        assertEquals(0, result);
    }

    @Test
    public void testSendMessageThrowsException() {
        Card card = Card.builder()
                .accountNumber("12345678-9")
                .age(20)
                .build();

        when(kafkaProperty.getTopicName()).thenReturn("testTopic");
        doThrow(new MessagingException("Error")).when(kafkaTemplate).send(any(Message.class));

        assertThrows(GenericException.class, () -> kafkaProducerAdapter.sendMessage(card));
    }

}
