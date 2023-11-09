package com.fxdealanalyzer.module;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
public class FxDealRequest {
    private String dealUniqueId;
    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private ZonedDateTime dealTimestamp;
    private BigDecimal dealAmount;
}
