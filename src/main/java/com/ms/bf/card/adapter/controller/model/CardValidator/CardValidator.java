package com.ms.bf.card.adapter.controller.model.CardValidator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CardValidator {
    private static final String ACCOUNT_NUMBER_REGEX =
            "^[0-9]{1,2}(\\.[0-9]{3})*-[0-9Kk]$";

    private static final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile(ACCOUNT_NUMBER_REGEX);


    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            return false;
        }

        Matcher matcher = ACCOUNT_NUMBER_PATTERN.matcher(accountNumber);

        if (!matcher.matches()) {
            return false;
        }
        return  true;
    }
/*
        String number = accountNumber.replaceAll("[^\\dKk]", "");

        if (number.length() < 7) {
            return false;
        }

        int firstDigit = Integer.parseInt(number.substring(0, 1));
        int secondDigit = Integer.parseInt(number.substring(1, 2));

        if (firstDigit < 1 || firstDigit > 9 || secondDigit < 1 || secondDigit > 9) {
            return false;
        }

        char lastDigit = number.charAt(number.length() - 1);

        if (lastDigit < '0' || lastDigit > '9' && lastDigit != 'K') {
            return false;
        }

        char providedDv = Character.toUpperCase(lastDigit);

        char calculatedDv = calculateVerifierDigit(number);

        return calculatedDv == providedDv;
    }

    private static char calculateVerifierDigit(String number) {
        int length = number.length();
        int sum = 0;
        int multiplier = 2;

        for (int i = length - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            sum += digit * multiplier;

            multiplier = (multiplier % 6 == 0) ? 2 : multiplier + 1;
        }

        int remainder = sum % 11;
        char result;

        if (remainder == 0) {
            result = '0';
        } else if (remainder == 1) {
            result = 'K';
        } else {
            result = Character.forDigit(11 - remainder, 10);
        }

        return result;*/




    public static boolean validateAge(int age) {
        return age >= 18 && age <= 99;

    }


}
