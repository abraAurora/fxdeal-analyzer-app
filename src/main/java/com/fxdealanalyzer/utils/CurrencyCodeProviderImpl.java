package com.fxdealanalyzer.utils;

import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class CurrencyCodeProviderImpl implements CurrencyCodeProvider {
    private final Set<String> validCurrencyCodes;

    private static CurrencyCodeProviderImpl instance = new CurrencyCodeProviderImpl();

    private CurrencyCodeProviderImpl() {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        Set<String> currencyCodes = new HashSet<>();

        for (Currency currency : availableCurrencies) {
            currencyCodes.add(currency.getCurrencyCode());
        }
        validCurrencyCodes = Collections.unmodifiableSet(currencyCodes);
    }

    public static CurrencyCodeProviderImpl getInstance() {
        return instance;
    }


    @Override
    public Set<String> getValidCurrencyCodes() {
        return validCurrencyCodes;
    }


}




