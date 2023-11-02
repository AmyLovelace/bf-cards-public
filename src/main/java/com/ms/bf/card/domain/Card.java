package com.ms.bf.card.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class Card{
    Account account;
    int cardNumber;
    int cardStatus;
    String descriptionStatus;
    int cardType;
    String descriptionType;
}
