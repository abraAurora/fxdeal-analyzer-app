package com.fxdealanalyzer.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FxDealResponse {

    private FxDealStatus status;
    private String description;
}
