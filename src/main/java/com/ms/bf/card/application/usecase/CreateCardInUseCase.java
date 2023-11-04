package com.ms.bf.card.application.usecase;

import com.ms.bf.card.adapter.kafka.KafkaProducerPort;
import com.ms.bf.card.application.port.in.CreateIn;
import com.ms.bf.card.application.port.out.CardRepository;
import com.ms.bf.card.config.ErrorCode;
import com.ms.bf.card.config.exception.CardException;
import com.ms.bf.card.domain.Card;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@AllArgsConstructor
@Slf4j
public class CreateCardInUseCase implements CreateIn {

    private final CardRepository createCardRepository;
    private final Executor executor;


    private final KafkaProducerPort kafkaProducerPort;
    @Override
    public Card create(Card card) {
        Card create = createCardRepository.create(card);
        if (card.getAccountNumber()!= null ) {
            return create;
        } throw new CardException(ErrorCode.CARD_INVALID_REQUEST);
    }

/*
    private CompletableFuture<Boolean> ifCardExists(Card card) {
        return CompletableFuture.supplyAsync(() -> {
            Card existingCard = null;
            try {
                existingCard = getCard.getCard(card.getCardNumber());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return existingCard != null;
        }, executor).exceptionally(exception -> {
            log.error("Ocurrió un error al obtener la información de la tarjeta", exception);
            return false;
        });
    }
*/

}

