package com.ms.bf.card.adapter.rest.card.model.card;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.bf.card.domain.Card;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardModel {

    private static final String CARD_NUMBER_PATTERN = "([0|1])";
    private static final String CARD_STATUS_PATTERN = "([2|3])";
    private static final String CARD_TYPE_PATTERN = "([4|5])";
    private static final int CARD_STATUS_ACTIVE = 2;
    private static final int CARD_STATUS_BLOCKED = 3;
    private static final String CARD_TYPE_STANDARD = "Standard";
    private static final String CARD_TYPE_PREMIUM = "Premium";


    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @NotBlank
    @NotEmpty(message = "el numero de cuenta debe tener un largo especifico")
    @JsonProperty("numero-cuenta")
    private String accountNumber;

    @JsonProperty("numero-tarjeta")
    @NotNull
    private int age;

    @JsonProperty("numero-tarjeta")
    @NotNull
    private int cardNumber;

    @JsonProperty("estado-tarjeta")
    @NotNull
    private int cardStatus;

    @JsonProperty("descripcion-estado")
    private String descriptionStatus;

    @JsonProperty("descripcion-memebresia")
    private String membership;

    public @javax.validation.constraints.NotNull String isStandard() {
        if(membership==CARD_TYPE_STANDARD){
            return CARD_TYPE_STANDARD;
        }else{
            return CARD_TYPE_PREMIUM;
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

    public Card toCardDomain() {
        return Card.builder()
                .accountNumber(this.accountNumber)
                .age(this.age)
                .cardNumber(this.cardNumber)
                .membership(isStandard())
                .cardStatus(isActive())
                .descriptionStatus(descriptionStatus())
                .build();

    }









}
