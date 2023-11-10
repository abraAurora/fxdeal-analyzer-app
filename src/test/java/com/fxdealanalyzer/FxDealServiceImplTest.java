package com.fxdealanalyzer;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealResponse;
import com.fxdealanalyzer.model.FxDealStatus;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.repository.entity.FxDealEntity;
import com.fxdealanalyzer.service.FxDealServiceImpl;
import com.fxdealanalyzer.service.validation.Validator;
import com.fxdealanalyzer.utils.FxDealRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FxDealServiceImplTest {

    @Mock
    private FxDealRepository fxDealRepository;

    @Mock
    private FxDealRequestMapper mapper;

    @Mock
    private Validator validatorChain;

    @InjectMocks
    private FxDealServiceImpl fxDealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void accept_ValidDeal_ReturnsAcceptedResponse() throws ValidationException {
        // Arrange
        FxDealRequest request = new FxDealRequest(/* provide valid input */);

        // Mock the behavior of validator and repository
        doNothing().when(validatorChain).validate(request);
        when(mapper.toEntity(request)).thenReturn(new FxDealEntity());

        // Act
        FxDealResponse response = fxDealService.accept(request);

        // Assert
        assertNotNull(response);
        assertEquals(FxDealStatus.ACCEPTED, response.getStatus());
        assertEquals(FxDealServiceImpl.DEAL_ACCEPTED_AND_SAVED_SUCCESSFULLY, response.getDescription());

        // Verify that methods were called
        verify(validatorChain, times(1)).validate(request);
        verify(fxDealRepository, times(1)).save(any());
    }

    @Test
    void accept_InvalidDeal_ThrowsValidationException() throws ValidationException {
        // Arrange
        FxDealRequest request = new FxDealRequest(/* provide invalid input */);

        // Mock the behavior of validator to throw an exception
        doThrow(new ValidationException("Invalid deal")).when(validatorChain).validate(request);

        // Act and Assert
        assertThrows(ValidationException.class, () -> fxDealService.accept(request));

        // Verify that methods were called
        verify(validatorChain, times(1)).validate(request);
        verify(fxDealRepository, never()).save(any());
    }
}
