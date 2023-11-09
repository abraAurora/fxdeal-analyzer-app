package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class DealAmountValidator implements FxDealService{
    public static final String DEAL_NOT_ACCEPTED_AMOUNT = "Deal not accepted because amount is not valid or exceeds acceptable range";

    @Autowired
    private FxDealService fxDealService;
    @Override
    public FxDealResponse accept(FxDealRequest request) {
        if(request.getDealAmount().compareTo(BigDecimal.ZERO) >= 0 && isFinite(request.getDealAmount())) {
            log.info("Deal amount is valid and within an acceptable range. Proceeding with acceptance.");
            return fxDealService.accept(request);
        }
        log.info("Deal amount is not valid or exceeds acceptable range. The deal will not be accepted.");
        return FxDealResponse
                .builder()
                .status(FxDealStatus.NOT_ACCEPTED)
                .description(DEAL_NOT_ACCEPTED_AMOUNT)
                .build();
    }
    private boolean isFinite(BigDecimal value) {
        return !value.equals(BigDecimal.ZERO) && !value.equals(BigDecimal.valueOf(Double.POSITIVE_INFINITY)) && !value.equals(BigDecimal.valueOf(Double.NEGATIVE_INFINITY));
    }
}
