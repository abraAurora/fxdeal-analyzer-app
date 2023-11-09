package com.fxdealanalyzer.service;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.service.validation.FxDealService;
import com.fxdealanalyzer.utils.FxDealRequestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Slf4j
public class FxDealServiceImpl implements FxDealService{
    public static final String DEAL_ACCEPTED_AND_SAVED_SUCCESSFULLY = "Deal is accepted and saved successfully";

    @Autowired
    private FxDealRepository fxDealRepository;

    @Autowired
    private FxDealRequestMapper mapper;

    public FxDealResponse accept(FxDealRequest request){
        log.info("Saving FX deal to the database: {}", request);
        fxDealRepository.save(mapper.toEntity(request));
        return FxDealResponse
                .builder()
                .status(FxDealStatus.ACCEPTED)
                .description(DEAL_ACCEPTED_AND_SAVED_SUCCESSFULLY)
                .build();
    }
}
