package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.repository.entity.FxDealEntity;
import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import com.fxdealanalyzer.repository.FxDealRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class FxDealRequestDuplicateValidator implements FxDealService{
    public static final String DEAL_NOT_ACCEPTED_DUPLICATE_ID = "Not accepted because a deal with the same ID already exists in the database";

    @Autowired
    private FxDealService fxDealService;

    @Autowired
    private FxDealRepository fxDealRepository;
    @Override
    public FxDealResponse accept(FxDealRequest request) {
        log.info("Querying the database for FX deal with unique ID: {}", request.getDealUniqueId());
        Optional<FxDealEntity> fxDealOptional = fxDealRepository.findByDealUniqueId(request.getDealUniqueId());
        if (fxDealOptional.isPresent()) {
            return fxDealService.accept(request);
        }
        log.info("An FX deal with the ID {} already exists in the database: {}", request.getDealUniqueId(), fxDealOptional);

        return FxDealResponse
                .builder()
                .status(FxDealStatus.NOT_ACCEPTED)
                .description(DEAL_NOT_ACCEPTED_DUPLICATE_ID)
                .build();
    }
}
