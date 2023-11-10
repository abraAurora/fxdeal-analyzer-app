package com.fxdealanalyzer;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.service.validation.CurrencyIsoCodeValidator;
import com.fxdealanalyzer.service.validation.Validator;
import com.fxdealanalyzer.utils.CurrencyCodeProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CurrencyIsoCodeValidatorTest {

    @Test
    void testValidCurrencyCodes() throws ValidationException {
        CurrencyCodeProvider currencyCodeProvider = Mockito.mock(CurrencyCodeProvider.class);
        Validator nextValidator = Mockito.mock(Validator.class);

        CurrencyIsoCodeValidator validator = new CurrencyIsoCodeValidator(currencyCodeProvider, nextValidator);

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));

        Set<String> validCurrencyCodes = new HashSet<>();

        validCurrencyCodes.add("USD");
        validCurrencyCodes.add("EUR");
        validCurrencyCodes.add("GBP");
        when(currencyCodeProvider.getValidCurrencyCodes()).thenReturn(validCurrencyCodes);

        doNothing().when(nextValidator).validate(request);

        validator.validate(request);

        verify(currencyCodeProvider, times(2)).getValidCurrencyCodes();

        verify(nextValidator, times(1)).validate(request);
    }

    @Test
    void testInvalidCurrencyCode() throws ValidationException {
        CurrencyCodeProvider currencyCodeProvider = Mockito.mock(CurrencyCodeProvider.class);
        Validator nextValidator = Mockito.mock(Validator.class);

        CurrencyIsoCodeValidator validator = new CurrencyIsoCodeValidator(currencyCodeProvider, nextValidator);

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "INVALID", ZonedDateTime.now(), BigDecimal.valueOf(10.9));
        Set<String> validCurrencyCodes = new HashSet<>();

        validCurrencyCodes.add("USD");
        validCurrencyCodes.add("EUR");
        validCurrencyCodes.add("GBP");
        when(currencyCodeProvider.getValidCurrencyCodes()).thenReturn(validCurrencyCodes);

        assertThrows(ValidationException.class, () -> validator.validate(request));

        verify(currencyCodeProvider, times(2)).getValidCurrencyCodes();

        verify(nextValidator, never()).validate(request);
    }
}