package com.ms.bf.card.application;

import com.ms.bf.card.adapter.kafka.KafkaProducerPort;
import com.ms.bf.card.application.port.in.CreateIn;
import com.ms.bf.card.application.usecase.CreateCardInUseCase;
import com.ms.bf.card.domain.Card;
import com.ms.bf.card.config.exception.GenericException;
import com.ms.bf.card.config.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateCardInUseCaseTest {

    @Mock
    private KafkaProducerPort kafkaProducerPort;

    @Mock
    private Card card;

    @InjectMocks
    private CreateCardInUseCase createCardInUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() throws GenericException, MessagingException {
        when(kafkaProducerPort.sendMessage(card)).thenReturn(0);

        Integer result = createCardInUseCase.create(card);

        assertEquals(0, result);
    }

    @Test
    public void testCreateThrowsException() throws GenericException, MessagingException {
        when(kafkaProducerPort.sendMessage(card)).thenThrow(new MessagingException("Error al generar el mensaje"));

        assertThrows(GenericException.class, () -> createCardInUseCase.create(card));
    }
    @Test
    public void testCreateCallsSendMessageWithCorrectArgument() throws GenericException, MessagingException {
        when(kafkaProducerPort.sendMessage(card)).thenReturn(0);

        Integer result = createCardInUseCase.create(card);

        verify(kafkaProducerPort).sendMessage(card);
    }
    @Test
    public void testCreateCallsSendMessageOnce() throws GenericException, MessagingException {
        when(kafkaProducerPort.sendMessage(card)).thenReturn(0);

        Integer result = createCardInUseCase.create(card);

        verify(kafkaProducerPort, times(1)).sendMessage(card);
    }
}
