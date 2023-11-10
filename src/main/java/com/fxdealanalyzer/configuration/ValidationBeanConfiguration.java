package com.fxdealanalyzer.configuration;

import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.service.validation.*;
import com.fxdealanalyzer.utils.CurrencyCodeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationBeanConfiguration {

    @Bean
    public Validator validator(CurrencyCodeProvider codeProvider, FxDealRepository fxDealRepository) {
        return new FxDealRequestDuplicateValidator(fxDealRepository,
                new CurrencyIsoCodeValidator(codeProvider,
                        new DealAmountValidator()
                ));
    }
}
