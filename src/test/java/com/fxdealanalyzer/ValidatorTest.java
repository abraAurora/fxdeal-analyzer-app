package com.fxdealanalyzer;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.service.validation.CurrencyIsoCodeValidator;
import com.fxdealanalyzer.service.validation.DealAmountValidator;
import com.fxdealanalyzer.service.validation.FxDealRequestDuplicateValidator;
import com.fxdealanalyzer.utils.CurrencyCodeProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ValidatorTest {
    private CurrencyCodeProvider currencyCodeProvider;
    @Mock
    private FxDealRepository fxDealRepository;

    @Test
    void testValidatorChain() throws ValidationException {
        DealAmountValidator dealAmountValidator = Mockito.mock(DealAmountValidator.class);
        CurrencyIsoCodeValidator codeValidator = new CurrencyIsoCodeValidator(currencyCodeProvider, dealAmountValidator);
        FxDealRequestDuplicateValidator duplicateValidator = new FxDealRequestDuplicateValidator(fxDealRepository, codeValidator);

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));

        doNothing().when(dealAmountValidator).validate(request);


        duplicateValidator.validate(request);

        verify(duplicateValidator, times(1)).validate(request);
        verify(codeValidator, times(1)).validate(request);
        verify(dealAmountValidator, times(1)).validate(request);
    }

    @Test
    void testValidatorChainWithException() throws ValidationException {
        DealAmountValidator dealAmountValidator = Mockito.mock(DealAmountValidator.class);
        CurrencyIsoCodeValidator codeValidator = new CurrencyIsoCodeValidator(currencyCodeProvider, dealAmountValidator);
        FxDealRequestDuplicateValidator duplicateValidator = new FxDealRequestDuplicateValidator(fxDealRepository, codeValidator);


        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));

        doThrow(new ValidationException("Validation failed in codeValidator")).when(codeValidator).validate(request);

        assertThrows(ValidationException.class, () -> duplicateValidator.validate(request));

        verify(duplicateValidator, times(1)).validate(request);
        verify(codeValidator, times(1)).validate(request);
        verify(dealAmountValidator, never()).validate(request);
    }
}
