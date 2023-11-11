package com.fxdealanalyzer;

import com.fxdealanalyzer.controller.FxDealController;
import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealResponse;
import com.fxdealanalyzer.model.FxDealStatus;
import com.fxdealanalyzer.service.FxDealService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FxDealControllerTest {
    @Mock
    private FxDealService fxDealService;
    @InjectMocks
    private FxDealController fxDealController;

    @Test
    void testAcceptFxDeal() throws ValidationException {
        FxDealRequest request = new FxDealRequest("uniqueId", "USD", "EUR", ZonedDateTime.now(), BigDecimal.valueOf(10.9));
        FxDealResponse mockResponse = FxDealResponse.builder()
                .status(FxDealStatus.ACCEPTED)
                .build();

        when(fxDealService.accept(request)).thenReturn(mockResponse);

        ResponseEntity<FxDealResponse> responseEntity = fxDealController.acceptFxDeal(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());

        verify(fxDealService, times(1)).accept(request);
    }
}
