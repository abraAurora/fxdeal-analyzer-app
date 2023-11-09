package com.fxdealanalyzer.service.validation;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class DealDateValidator implements FxDealService{
    public static final String DEAL_NOT_ACCEPTED_FUTURE = "Deal not accepted because FX deal timestamp is in the future";

    @Autowired
    private FxDealService fxDealService;
    @Override
    public FxDealResponse accept(FxDealRequest request) {
        log.info("Checking FX deal timestamp for acceptance...");
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneOffset.UTC);
        if(!request.getDealTimestamp().isAfter(currentDateTime)) {
            log.info("FX deal timestamp is within an acceptable range.");
            return fxDealService.accept(request);
        }
        log.info("FX deal timestamp is in the future and will not be accepted.");
        return FxDealResponse
                .builder()
                .status(FxDealStatus.NOT_ACCEPTED)
                .description(DEAL_NOT_ACCEPTED_FUTURE)
                .build();


    }
}
