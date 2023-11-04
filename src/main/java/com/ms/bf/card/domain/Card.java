package com.ms.bf.card.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Card{
    String accountNumber;
    int age;
    int cardNumber;
    int cardStatus;
    String descriptionStatus;
    String membership;
}
