package com.ms.bf.card.adapter.kafka;

import com.ms.bf.card.domain.Card;

public interface KafkaProducerPort {

    Integer sendMessage(Card card);
}
