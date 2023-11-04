package com.ms.bf.card.adapter.rest.card;

import com.ms.bf.card.adapter.rest.exception.BadRequestRestClientException;
import com.ms.bf.card.adapter.rest.exception.EmptyOrNullBodyRestClientException;
import com.ms.bf.card.adapter.rest.exception.NotFoundRestClientException;
import com.ms.bf.card.adapter.rest.exception.TimeoutRestClientException;
import com.ms.bf.card.adapter.rest.handler.RestTemplateErrorHandler;
import com.ms.bf.card.adapter.rest.card.model.card.CardModel;
import com.ms.bf.card.application.port.out.CardRepository;
import com.ms.bf.card.config.ErrorCode;
import com.ms.bf.card.domain.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class CardRestClientAdapter implements CardRepository{

    private final RestTemplate restTemplate;



    public CardRestClientAdapter( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        var errorHandler = new RestTemplateErrorHandler(
                Map.of(
                        HttpStatus.NOT_FOUND, new NotFoundRestClientException(ErrorCode.ACCOUNT_NOT_FOUND),
                        HttpStatus.REQUEST_TIMEOUT, new TimeoutRestClientException(ErrorCode.CARD_TIMEOUT),
                        HttpStatus.BAD_REQUEST, new BadRequestRestClientException(ErrorCode.CARD_BAD_REQUEST)
                )
        );
        this.restTemplate.setErrorHandler(errorHandler);
    }

    @Override
    public Card create(Card card) {
        log.info("Servicio crear tarjeta, lo conecta a: [{}]" ,property.getUrlCreates(property.getUrlCreate()));
        CardModel response = Optional.ofNullable(restTemplate.postForObject(property.getUrlCreates(property.getUrlCreate()),card, CardModel.class))
                .orElseThrow(()-> new EmptyOrNullBodyRestClientException(ErrorCode.INTERNAL_ERROR));
        log.info("popo[{}]", response);
        return response.toCardDomain();
    }


}
