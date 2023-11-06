package com.ms.bf.card.application.port.out;

import com.ms.bf.card.domain.Card;


public interface CardRepository {

    Card create(Card card);


}
