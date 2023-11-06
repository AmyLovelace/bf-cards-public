package com.ms.bf.card.adapter;

import com.ms.bf.card.adapter.controller.model.card.CardRest;
import com.ms.bf.card.domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CardRestTest {

    @Mock
    private Card card;

    @InjectMocks
    private CardRest cardRest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToCardRest() {
        when(card.getAccountNumber()).thenReturn("12345678-9");
        when(card.getAge()).thenReturn(20);
        when(card.getCardNumber()).thenReturn("123456789012");
        when(card.getCardStatus()).thenReturn(2);
        when(card.getDescriptionStatus()).thenReturn("Activo");
        when(card.getMembership()).thenReturn("Standard");

        CardRest result = CardRest.toCardRest(card);

        assertEquals("12345678-9", result.getAccountNumber());
        assertEquals(20, result.getAge());
        assertEquals("123456789012", result.getCardNumber());
        assertEquals(2, result.getCardStatus());
        assertEquals("Activo", result.getDescriptionStatus());
        assertEquals("Standard", result.getMembership());
    }

    @Test
    public void testToCardDomain() {
        CardRest cardRest = CardRest.builder()
                .accountNumber("12345678-9")
                .age(20)
                .cardNumber("123456789012")
                .cardStatus(2)
                .descriptionStatus("Activo")
                .membership("Standard")
                .build();

        Card result = cardRest.toCardDomain();

        assertEquals("12345678-9", result.getAccountNumber());
        assertEquals(20, result.getAge());
        assertEquals("123456789012", result.getCardNumber());
        assertEquals(2, result.getCardStatus());
        assertEquals("Activo", result.getDescriptionStatus());
        assertEquals("Standard", result.getMembership());
    }
    @Test
    public void testToCardDomainInvalidAccountNumber() {
        CardRest cardRest = CardRest.builder()
                .accountNumber("12345678-9")
                .age(20)
                .build();

        assertThrows(IllegalArgumentException.class, () -> cardRest.toCardDomain());
    }
    @Test
    public void testToCardDomainAgeUnder18() {
        CardRest cardRest = CardRest.builder()
                .accountNumber("12345678-9")
                .age(17)
                .build();

        assertThrows(IllegalArgumentException.class, () -> cardRest.toCardDomain());
    }
    @Test
    public void testIsStandardInvalidMembership() {
        CardRest cardRest = CardRest.builder()
                .accountNumber("12345678-9")
                .age(20)
                .membership("Invalid")
                .build();

        String result = cardRest.isStandard();

        assertEquals("Standard", result);
    }

    @Test
    public void testToCardDomainThrowsException() {
        CardRest cardRest = CardRest.builder()
                .accountNumber("12345678-9")
                .age(17)
                .build();

        assertThrows(IllegalArgumentException.class, () -> cardRest.toCardDomain());


    }

}
