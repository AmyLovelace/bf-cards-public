package com.ms.bf.card.adapter.controller.model.CardValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardValidator {

    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]{1,3}(\\.[0-9]{3})*-[0-9Kk]$";
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(ACCOUNT_NUMBER_REGEX);

    public static boolean isValidAccountNumber(String accountNumber) {
        Matcher matcher = ACCOUNT_NUMBER_PATTERN.matcher(accountNumber);
        return matcher.matches();
    }

    public static boolean isValidAge(int age) {
        return age >= 18;
    }
    public static boolean isValidCardStatus(Integer cardStatus) {
        return cardStatus == null || cardStatus != CARD_STATUS_BLOCKED;
    }
}
