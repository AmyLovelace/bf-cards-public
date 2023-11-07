package com.ms.bf.card.adapter.controller.model.CardValidator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CardValidator {

    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]{1,3}(\\.[0-9]{3})*-[0-9Kk]$";
    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(ACCOUNT_NUMBER_REGEX);
    // Assuming there's a specific regex for card number validation, replace "Your_Card_Number_Regex" with the actual regex

    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            return false;
        }
        Matcher matcher = ACCOUNT_NUMBER_PATTERN.matcher(accountNumber);
        return matcher.matches();
    }

    public static boolean validateAge(int age) {
        return age >= 18;
    }


}
