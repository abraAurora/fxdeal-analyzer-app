package com.fxdealanalyzer.controller;

import com.fxdealanalyzer.exception.ValidationException;
import com.fxdealanalyzer.model.FxDealRequest;
import com.fxdealanalyzer.model.FxDealResponse;
import com.fxdealanalyzer.service.FxDealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fx-deals")
public class FxDealController {
    private FxDealService fxDealService;

    public FxDealController(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/accept")
    public ResponseEntity<FxDealResponse> acceptFxDeal(@RequestBody FxDealRequest request) throws ValidationException {
        FxDealResponse response = fxDealService.accept(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

