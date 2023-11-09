package com.fxdealanalyzer.configuration;

import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.service.FxDealServiceImpl;
import com.fxdealanalyzer.service.validation.*;
import com.fxdealanalyzer.utils.CurrencyCodeUtil;
import com.fxdealanalyzer.utils.FxDealRequestMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationBeanConfiguration {

    @Bean
    public FxDealService fxDealService(CurrencyCodeUtil currencyCodeUtil, FxDealRepository fxDealRepository, FxDealRequestMapper mapper){
        return new FxDealRequestDuplicateValidator(
               new DealDateValidator(
                       new DealAmountValidator(
                               new CurrencyIsoCodeValidator(currencyCodeUtil,
                                       new FxDealServiceImpl(fxDealRepository,mapper)
                               )
                       )
               )
        ,fxDealRepository);
    }
}
