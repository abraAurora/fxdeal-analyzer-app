package com.fxdealanalyzer.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class FxDealRequest {
    @NotBlank
    private String dealUniqueId;
    @NotBlank
    private String fromCurrencyIsoCode;
    @NotBlank
    private String toCurrencyIsoCode;
    @NotNull
    @Past
    private ZonedDateTime dealTimestamp;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal dealAmount;
}
