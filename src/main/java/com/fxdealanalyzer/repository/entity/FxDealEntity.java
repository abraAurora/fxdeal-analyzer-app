package com.fxdealanalyzer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "fx_deals",indexes = {
        @Index(name = "unique_deal_id", columnList = "deal_unique_id", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
public class FxDealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id")
    private Long dealId;

    @Column(name = "deal_unique_id")
    private String dealUniqueId;

    @Column(name = "from_currency_iso_code")
    private String fromCurrencyIsoCode;

    @Column(name = "to_currency_iso_code")
    private String toCurrencyIsoCode;

    @Column(name = "deal_timestamp")
    private ZonedDateTime dealTimestamp;

    @Column(name = "deal_amount")
    private BigDecimal dealAmount;

    @Column(name = "imported_at")
    private ZonedDateTime importedAt;

}
