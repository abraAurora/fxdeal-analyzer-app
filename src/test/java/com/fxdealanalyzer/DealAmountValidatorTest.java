package com.fxdealanalyzer;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.service.validation.DealAmountValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
public class DealAmountValidatorTest {

    @Test
    void testValidDealAmount() throws ValidationException {

        DealAmountValidator validator = new DealAmountValidator();

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(1000));

        assertDoesNotThrow(() -> validator.validate(request));

    }

    @Test
    void testInvalidDealAmount() {
        DealAmountValidator validator = new DealAmountValidator();

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(Double.POSITIVE_INFINITY) /* other fields */);

        assertThrows(ValidationException.class, () -> validator.validate(request));
    }
}
