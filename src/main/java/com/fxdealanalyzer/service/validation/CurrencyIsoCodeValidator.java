package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealStatus;
import com.fxdealanalyzer.service.FxDealService;
import com.fxdealanalyzer.utils.CurrencyCodeProvider;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CurrencyIsoCodeValidator implements Validator {

    private Validator nextValidator;

    private CurrencyCodeProvider currencyCodeProvider;


    public CurrencyIsoCodeValidator(CurrencyCodeProvider currencyCodeProvider,Validator nextValidator) {
        this.nextValidator = nextValidator;
        this.currencyCodeProvider = currencyCodeProvider;
    }

    @Override
    public void validate(FxDealRequest request) throws ValidationException {
        if (isValidCurrencyCode(request.getFromCurrencyIsoCode())
                && isValidCurrencyCode(request.getToCurrencyIsoCode())) {
            log.info("Both currency codes are valid. Proceeding with acceptance for deal with id {}.", request.getDealUniqueId());
            nextValidator.validate(request);

        } else {
            log.info("At least one of the currency codes is not valid. The deal with id {} will not be accepted.", request.getDealUniqueId());
            throw new ValidationException("At least one of the currency codes is not valid");
        }
    }

    private boolean isValidCurrencyCode(String code) {
        return currencyCodeProvider.getValidCurrencyCodes().contains(code);
    }
}
