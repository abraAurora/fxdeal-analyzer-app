package com.fxdealanalyzer.service;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealResponse;
import com.fxdealanalyzer.model.FxDealStatus;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.service.validation.Validator;
import com.fxdealanalyzer.utils.FxDealRequestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FxDealServiceImpl implements FxDealService{
    public static final String DEAL_ACCEPTED_AND_SAVED_SUCCESSFULLY = "Deal is accepted and saved successfully";

    private FxDealRepository fxDealRepository;

    private FxDealRequestMapper mapper;

    private Validator validatorChain;

    public FxDealResponse accept(FxDealRequest request) throws ValidationException {
        log.info("Validating request with id {} using the validator chain...", request.getDealUniqueId());
        validatorChain.validate(request);
        log.info("Saving FX deal to the database: {}", request);
        fxDealRepository.save(mapper.toEntity(request));
        return FxDealResponse
                .builder()
                .status(FxDealStatus.ACCEPTED)
                .description(DEAL_ACCEPTED_AND_SAVED_SUCCESSFULLY)
                .build();
    }
}
