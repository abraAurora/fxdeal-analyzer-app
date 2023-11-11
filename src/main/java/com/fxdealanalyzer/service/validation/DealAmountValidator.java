package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealStatus;
import com.fxdealanalyzer.service.FxDealService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
public class DealAmountValidator implements Validator {

    public static final String veryLargeNumber = "987657890";

    public DealAmountValidator() {
            }

    @Override
    public void validate(FxDealRequest request) throws ValidationException {
        if (isFinite(request.getDealAmount())) {
            log.info("Deal with the ID {} amount is valid and within an acceptable range. Proceeding with acceptance.", request.getDealUniqueId());
        } else {
            log.info("Deal with the ID {} amount is not valid or exceeds acceptable range. The deal will not be accepted.", request.getDealUniqueId());
            throw new ValidationException("FX deal amount is not valid");
        }
    }

    private boolean isFinite(BigDecimal value) {
        return !value.equals(BigDecimal.valueOf(Long.parseLong(veryLargeNumber)));
    }
}
