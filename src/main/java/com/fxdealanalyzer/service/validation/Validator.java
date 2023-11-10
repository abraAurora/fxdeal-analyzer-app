package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;

public interface Validator {

    void validate(FxDealRequest request) throws ValidationException;
}
