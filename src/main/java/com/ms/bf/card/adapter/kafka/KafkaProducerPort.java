package com.ms.bf.card.adapter.kafka;

import com.ms.bf.card.domain.Account;

public interface KafkaProducerPort {

    Integer sendMessage(Account account);
}
