package com.fxdealanalyzer;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.repository.entity.FxDealEntity;
import com.fxdealanalyzer.service.validation.FxDealRequestDuplicateValidator;
import com.fxdealanalyzer.service.validation.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
public class FxDealRequestDuplicateValidatorTest {

    @Test
    void testValidRequest() throws ValidationException {
        FxDealRepository fxDealRepository = Mockito.mock(FxDealRepository.class);
        Validator nextValidator = Mockito.mock(Validator.class);

        FxDealRequestDuplicateValidator validator = new FxDealRequestDuplicateValidator(fxDealRepository, nextValidator);

        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));

        when(fxDealRepository.findByDealUniqueId(request.getDealUniqueId())).thenReturn(Optional.empty());

        doNothing().when(nextValidator).validate(request);

        validator.validate(request);

        verify(fxDealRepository, times(1)).findByDealUniqueId(request.getDealUniqueId());

        verify(nextValidator, times(1)).validate(request);
    }

    @Test
    void testDuplicateRequest() throws ValidationException {
        FxDealRepository fxDealRepository = Mockito.mock(FxDealRepository.class);
        Validator nextValidator = Mockito.mock(Validator.class);

        FxDealRequestDuplicateValidator validator = new FxDealRequestDuplicateValidator(fxDealRepository, nextValidator);

        FxDealRequest request = new FxDealRequest("duplicateId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));

        when(fxDealRepository.findByDealUniqueId(request.getDealUniqueId())).thenReturn(Optional.of(new FxDealEntity()));

        assertThrows(ValidationException.class, () -> validator.validate(request));

        verify(fxDealRepository, times(1)).findByDealUniqueId(request.getDealUniqueId());

        verify(nextValidator, never()).validate(request);
    }
}
