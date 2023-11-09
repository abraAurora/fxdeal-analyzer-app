package com.fxdealanalyzer.utils;

import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
@Service
public class CurrencyCodeUtil {
    private static Set<String> validCurrencyCodes = initValidCurrencyCodes();

    private static Set<String> initValidCurrencyCodes() {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        Set<String> currencyCodes = new HashSet<>();

        for (Currency currency : availableCurrencies) {
            currencyCodes.add(currency.getCurrencyCode());
        }

        return currencyCodes;
    }

    public boolean isValidCurrencyCode(String code) {
        return validCurrencyCodes.contains(code);
    }

}




