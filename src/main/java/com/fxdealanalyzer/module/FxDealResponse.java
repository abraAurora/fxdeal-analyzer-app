package com.fxdealanalyzer.module;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FxDealResponse {

    private FxDealStatus status;
    private String description;
}
