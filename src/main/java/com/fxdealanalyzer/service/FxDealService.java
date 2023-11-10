package com.fxdealanalyzer.service;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealResponse;

public interface FxDealService {
    public FxDealResponse accept(FxDealRequest request) throws ValidationException;
    }
