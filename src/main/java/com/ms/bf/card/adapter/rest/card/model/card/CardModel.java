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
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardModel {

    private static final String CARD_NUMBER_PATTERN = "([0|1])";
    private static final String CARD_STATUS_PATTERN = "([2|3])";
    private static final String CARD_TYPE_PATTERN = "([4|5])";
    private static final int CARD_STATUS_ACTIVE = 2;
    private static final int CARD_STATUS_BLOCKED = 3;
    private static final String CARD_MEMBERSHIP_STANDARD = "Standard";
    private static final String CARD_MEMBERSHIP_PREMIUM = "Premium";
    private static final int DEFAULT_CARD_STATUS = CARD_STATUS_ACTIVE;
    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]{1,3}(\\.[0-9]{3})*-[0-9Kk]$";


    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @NotBlank
    @NotEmpty(message = "el numero de cuenta debe tener un largo especifico")
    @JsonProperty("cuenta")
    private String accountNumber;

    @JsonProperty("edad")
    @NotNull
    private int age;

    @JsonProperty("numero-tarjeta")
    @NotNull
    private String cardNumber;

    @JsonProperty("estado-tarjeta")
    @NotNull
    private int cardStatus = DEFAULT_CARD_STATUS;

    @JsonProperty("descripcion-estado")
    private String descriptionStatus;

    @JsonProperty("descripcion-tipo")
    private String membership;


    public static boolean isValidAccountNumber(String accountNumber) {
        Pattern pattern = Pattern.compile(ACCOUNT_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(accountNumber);
        return matcher.matches();
    }
    public String isStandard() {
        if (membership == null || membership.isEmpty()) {
            return CARD_MEMBERSHIP_STANDARD;
        } else if (membership.equals(CARD_MEMBERSHIP_PREMIUM)) {
            return CARD_MEMBERSHIP_PREMIUM;
        } else {
            return CARD_MEMBERSHIP_STANDARD;
        }
    }

    public int isActive() {
        if (cardStatus == CARD_STATUS_BLOCKED) {
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



    public Card toCardDomain() {
        if (!isValidAccountNumber(this.accountNumber)) {
            throw new IllegalArgumentException("El número de cuenta no tiene el formato de RUT válido.");
        }
        if (this.age < 18) {
            throw new IllegalArgumentException("El usuario debe tener al menos 18 años para abrir una cuenta.");
        }
        String generatedCardNumber = generateCardNumber();

        return Card.builder()
                .accountNumber(this.accountNumber)
                .age(this.age)
                .cardNumber(generatedCardNumber)
                .membership(isStandard())
                .cardStatus(isActive())
                .descriptionStatus(descriptionStatus())
                .build();

    }









}
