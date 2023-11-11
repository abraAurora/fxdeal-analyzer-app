package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.repository.entity.FxDealEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class FxDealRequestDuplicateValidator implements Validator {

    private FxDealRepository fxDealRepository;

    private Validator nextValidator;

    public FxDealRequestDuplicateValidator(FxDealRepository fxDealRepository, Validator nextValidator) {
        this.fxDealRepository = fxDealRepository;
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(FxDealRequest request) throws ValidationException {
        log.info("Querying the database for FX deal with unique ID: {}", request.getDealUniqueId());
        Optional<FxDealEntity> fxDealOptional = fxDealRepository.findByDealUniqueId(request.getDealUniqueId());
        if (fxDealOptional.isPresent()) {
            log.info("An FX deal with the ID {} already exists in the database: {}", request.getDealUniqueId(), fxDealOptional);
            throw new ValidationException("Duplicate ID encountered already exists in the database");
        }
        nextValidator.validate(request);


    }
}
