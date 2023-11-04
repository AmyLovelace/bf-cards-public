package com.ms.bf.card.adapter.kafka;

import com.ms.bf.card.domain.Card;

public interface KafkaProducerPort {

    public Integer SendMessage(Card card);
}
