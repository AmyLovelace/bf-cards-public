package com.ms.bf.card.adapter;

import com.ms.bf.card.adapter.controller.card.CardControllerAdapter;
import com.ms.bf.card.adapter.controller.model.RestResponse;
import com.ms.bf.card.adapter.controller.model.card.CardRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CardControllerAdapterTest {

    @Mock
    private CardRest cardRest;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private CardControllerAdapter cardControllerAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCardWithValidCardRest() throws NullPointerException, InterruptedException, ExecutionException {
        when(cardRest.getAccountNumber()).thenReturn("12.345.678-9");
        when(cardRest.getAge()).thenReturn(20);
        when(cardRest.getCardStatus()).thenReturn(2);
        when(cardRest.getDescriptionStatus()).thenReturn("Activo");
        when(cardRest.getMembership()).thenReturn("Standard");

        RestResponse<Integer> result = cardControllerAdapter.createCard(httpServletRequest, cardRest);

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertNotNull(result.getData());
    }

    @Test
    public void testCreateCardWithInvalidCardRest() {
        when(cardRest.getAge()).thenReturn(17);

        assertThrows(IllegalArgumentException.class, () -> cardControllerAdapter. createCard(httpServletRequest, cardRest));
    }
}
