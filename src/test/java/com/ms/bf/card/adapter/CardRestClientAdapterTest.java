package com.ms.bf.card.adapter;

package com.ms.bf.card.adapter.rest.card;

import com.ms.bf.card.adapter.rest.card.CardRestClientAdapter;
import com.ms.bf.card.adapter.rest.exception.BadRequestRestClientException;
import com.ms.bf.card.adapter.rest.exception.EmptyOrNullBodyRestClientException;
import com.ms.bf.card.adapter.rest.exception.NotFoundRestClientException;
import com.ms.bf.card.adapter.rest.exception.TimeoutRestClientException;
import com.ms.bf.card.adapter.rest.handler.RestTemplateErrorHandler;
import com.ms.bf.card.adapter.rest.card.model.card.CardModel;
import com.ms.bf.card.application.port.out.CardRepository;
import com.ms.bf.card.config.ErrorCode;
import com.ms.bf.card.config.property.KafkaProperty;
import com.ms.bf.card.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CardRestClientAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CardRestClientAdapter cardRestClientAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() {
        Card card = new Card();
        CardModel cardModel = new CardModel();
        cardModel.(card);

        when(restTemplate.postForObject(null, card, CardModel.class)).thenReturn(cardModel);

        Card result = cardRestClientAdapter.create(card);

        assertEquals(card, result);
    }

    @Test
    public void testCreateThrowsException() {
        Card card = new Card();

        when(restTemplate.postForObject(null, card, CardModel.class)).thenThrow(new EmptyOrNullBodyRestClientException(ErrorCode.INTERNAL_ERROR));

        assertThrows(EmptyOrNullBodyRestClientException.class, () -> cardRestClientAdapter.create(card));
    }

    // Add more tests as needed
}
