package com.ms.bf.card.adapter.controller.model.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.bf.card.domain.Card;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardRest {

    private static final String CARD_NUMBER_PATTERN = "([0|1])";
    private static final String CARD_STATUS_PATTERN = "([2|3])";
    private static final String CARD_TYPE_PATTERN = "([4|5])";
    private static final int CARD_STATUS_ACTIVE = 2;
    private static final int CARD_STATUS_BLOCKED = 3;
    private static final String CARD_TYPE_STANDARD = "Standard";
    private static final String CARD_TYPE_PREMIUM = "Premium";

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @JsonProperty("cuenta")
    String accountNumber;

    @JsonProperty("numero-tarjeta")
    @NotNull
    int age;

    @JsonProperty("numero-tarjeta")
    @NotNull
    int cardNumber;


    @JsonProperty("estado-tarjeta")
    @NotNull
    int cardStatus;

    @JsonProperty("descripcion-estado")
    String descriptionStatus;

    @JsonProperty("descripcion-tipo")
    String membership;

    public String isStandard() {
        if (membership == CARD_TYPE_STANDARD) {
            return CARD_TYPE_STANDARD;
        } else {
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

    public static CardRest toCardRest(Card card) {
        return Objects.nonNull(card)?
                CardRest.builder()
                        .accountNumber(card.getAccountNumber())
                        .age(card.getAge())
                        .cardNumber(card.getCardNumber())
                        .cardStatus(card.getCardStatus())
                        .descriptionStatus(card.getDescriptionStatus())
                        .membership(card.getMembership())
                        .build() : null;
    }

    public Card toCardDomain() {
        return Card.builder()
                        .accountNumber(this.accountNumber)
                        .age(this.age)
                        .cardNumber(this.cardNumber)
                        .cardStatus(isActive())
                        .descriptionStatus(descriptionStatus())
                        .membership(isStandard())
                        .build() ;
    }

}
