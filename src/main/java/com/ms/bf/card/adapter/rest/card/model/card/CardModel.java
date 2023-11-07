package com.ms.bf.card.adapter.rest.card.model.card;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ms.bf.card.config.ErrorCode;
import com.ms.bf.card.config.exception.CardException;
import com.ms.bf.card.domain.Card;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

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

    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]{1,3}(\\.[0-9]{3})*-[0-9Kk]$";
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(ACCOUNT_NUMBER_REGEX);

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NotNull
    @javax.validation.constraints.Pattern(regexp = ACCOUNT_NUMBER_REGEX, message = "El número de cuenta no tiene el formato de RUT válido.")
    @NotEmpty(message = "el numero de cuenta debe tener un largo especifico")
    @JsonProperty("cuenta")
    private String accountNumber;

    @JsonProperty("edad")
    @NotNull
    private int age;

    public Card toCardDomain() {


        return Card.builder()
                .accountNumber(this.accountNumber)
                .age(this.age)
                .build();

    }

}
