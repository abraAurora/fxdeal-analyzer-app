package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;

public interface FxDealService {
    public FxDealResponse accept(FxDealRequest request);
    }
