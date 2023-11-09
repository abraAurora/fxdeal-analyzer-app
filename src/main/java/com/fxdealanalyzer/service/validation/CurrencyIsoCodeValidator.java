package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import com.fxdealanalyzer.utils.CurrencyCodeUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CurrencyIsoCodeValidator implements FxDealService{
    public static final String DEAL_NOT_ACCEPTED_INVALID_CURRENCY = "Deal not accepted because At least one of the currency codes is not valid";


    @Autowired
    private CurrencyCodeUtil currencyCodeUtil;

    @Autowired
    private FxDealService fxDealService;


    @Override
    public FxDealResponse accept(FxDealRequest request) {
        if(currencyCodeUtil.isValidCurrencyCode(request.getFromCurrencyIsoCode())
                && currencyCodeUtil.isValidCurrencyCode(request.getToCurrencyIsoCode())) {
            log.info("Both currency codes are valid. Proceeding with acceptance.");
            return fxDealService.accept(request);
        }
        log.warn("At least one of the currency codes is not valid. The deal will not be accepted.");
        return FxDealResponse
                .builder()
                .status(FxDealStatus.NOT_ACCEPTED)
                .description(DEAL_NOT_ACCEPTED_INVALID_CURRENCY)
                .build();
    }
}
