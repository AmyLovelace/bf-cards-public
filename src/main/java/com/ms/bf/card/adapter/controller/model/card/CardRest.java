package com.ms.bf.card.adapter.controller.model.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.bf.card.domain.Card;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.Matcher;


@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardRest {

    private static final int CARD_STATUS_ACTIVE = 2;
    private static final int CARD_STATUS_BLOCKED = 3;
    private static final String CARD_TYPE_STANDARD = "Standard";
    private static final String CARD_TYPE_PREMIUM = "Premium";
    private static final int DEFAULT_CARD_STATUS = CARD_STATUS_ACTIVE;
    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]{1,3}(\\.[0-9]{3})*-[0-9Kk]$";
    private static final java.util.regex.Pattern ACCOUNT_NUMBER_PATTERN = java.util.regex.Pattern.compile(ACCOUNT_NUMBER_REGEX);

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @Pattern(regexp = ACCOUNT_NUMBER_REGEX, message = "El número de cuenta no tiene el formato de RUT válido.")
    @NotEmpty(message = "el numero de cuenta debe tener un largo especifico")
    @JsonProperty("cuenta")
    String accountNumber;

    @JsonProperty("edad")
    @NotNull
    int age;

    @JsonProperty("numero-tarjeta")
    String cardNumber;


    @JsonProperty("estado-tarjeta")
    Integer cardStatus;

    @JsonProperty("descripcion-estado")
    String descriptionStatus;

    @JsonProperty("descripcion-tipo")
    String membership;

    public static CardRest toCardRest(Card card) {
        if (card == null) {
            return null;
        }

        return CardRest.builder()
                .accountNumber(card.getAccountNumber())
                .age(card.getAge())
                .cardNumber(card.getCardNumber())
                .cardStatus(card.getCardStatus())
                .descriptionStatus(card.getDescriptionStatus())
                .membership(card.getMembership())
                .build();
    }


    public Card toCardDomain() {
        if (!isValidAccountNumber(this.accountNumber)) {
            throw new IllegalArgumentException("El número de cuenta no tiene el formato de RUT válido.");
        }
        if (this.age < 18) {
            throw new IllegalArgumentException("El usuario debe tener al menos 18 años para abrir una cuenta.");
        }
        int cardStatusValue = (this.cardStatus == null || this.cardStatus != CARD_STATUS_BLOCKED) ? DEFAULT_CARD_STATUS : this.cardStatus;

        String generatedCardNumber = generateCardNumber();


        return Card.builder()
                .accountNumber(this.accountNumber)
                .age(this.age)
                .cardNumber(generatedCardNumber)
                .cardStatus(cardStatusValue)
                .descriptionStatus(descriptionStatus())
                .membership(isStandard())
                .build();
    }
    public static boolean isValidAccountNumber(String accountNumber) {
        Matcher matcher = ACCOUNT_NUMBER_PATTERN.matcher(accountNumber);
        return matcher.matches();
    }

    private String generateCardNumber() {

            StringBuilder number = new StringBuilder();

            for (int i = 0; i < 12; i++) {
                if (i > 0 && i % 3 == 0) {
                    number.append("-");
                }
                int digit = (int) (Math.random() * 10);
                number.append(digit);
            }

            return number.toString();

    }

    public String isStandard() {
        if (membership == null || membership.isEmpty()) {
            return CARD_TYPE_STANDARD;
        } else if (membership.equals(CARD_TYPE_PREMIUM)) {
            return CARD_TYPE_PREMIUM;
        } else {
            return CARD_TYPE_STANDARD;
        }
    }

    public int isActive() {
        if (cardStatus == CARD_STATUS_BLOCKED) {
            return CARD_STATUS_BLOCKED;
        } else {
            return CARD_STATUS_ACTIVE;
        }
    }

    public String descriptionStatus() {
        if (isActive() == 2) {
            setDescriptionStatus("Activo");
        } else {
            setDescriptionStatus("Bloqueado");
        }
        return descriptionStatus;
    }
}

