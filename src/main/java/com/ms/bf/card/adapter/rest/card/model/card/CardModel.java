package com.ms.bf.card.adapter.rest.card.model.card;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.bf.card.adapter.rest.card.model.account.AccountModel;
import com.ms.bf.card.domain.Card;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardModel {

    private static final String CARD_NUMBER_PATTERN = "([0|1])";
    private static final String CARD_STATUS_PATTERN = "([2|3])";
    private static final String CARD_TYPE_PATTERN = "([4|5])";
    private static final int CARD_STATUS_ACTIVE = 2;
    private static final int CARD_STATUS_BLOCKED = 3;
    private static final int CARD_TYPE_TITULAR = 4;
    private static final int CARD_TYPE_ADDITIONAL = 5;


    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @NotBlank
    @NotEmpty(message = "el numero de cuenta debe tener un largo especifico")
    @JsonProperty("numero-cuenta")
    private AccountModel account;

    @JsonProperty("numero-tarjeta")
    @NotNull
    private int cardNumber;

    @JsonProperty("tipo-tarjeta")
    @NotNull
    private int cardType;

    @JsonProperty("estado-tarjeta")
    @NotNull
    private int cardStatus;

    @JsonProperty("descripcion-estado")
    private String descriptionStatus;

    @JsonProperty("descripcion-tipo")
    private String descriptionType;

    public @javax.validation.constraints.NotNull int isTitular() {
        if(cardType==4){
            return CARD_TYPE_TITULAR;
        }else{
            return CARD_TYPE_ADDITIONAL;
        }

    }

    public int isActive() {
        if (cardStatus == 3) {
            return CARD_STATUS_BLOCKED;
        } else {
            return CARD_STATUS_ACTIVE;
        }
    }


    public String descriptionStatus(){
        if(isActive()==2){
            setDescriptionStatus("Activo");
        }else{
            setDescriptionStatus("Bloqueado");
        }
        return descriptionStatus;

    };
    public String descriptionType(){
        if(isTitular()==4){
            setDescriptionType("Titular");
        }else{
            setDescriptionType("Adicional");
        }
        return descriptionType;

    };
    public Card toCardDomain() {
        return Card.builder()
                .account(this.account.toAccountDomain())
                .cardNumber(this.cardNumber)
                .cardType(isTitular())
                .cardStatus(isActive())
                .descriptionType(descriptionType())
                .descriptionStatus(descriptionStatus())
                .build();

    }









}
