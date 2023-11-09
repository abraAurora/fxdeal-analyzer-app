package com.fxdealanalyzer.controller;

import com.fxdealanalyzer.module.FxDealRequest;
import com.fxdealanalyzer.module.FxDealResponse;
import com.fxdealanalyzer.module.FxDealStatus;
import com.fxdealanalyzer.service.validation.FxDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fx-deals")
public class FxDealController {
    @Autowired
    private FxDealService fxDealService;


    @PostMapping("/accept")
    public ResponseEntity<FxDealResponse> acceptFxDeal(@RequestBody FxDealRequest request) {
        FxDealResponse response = fxDealService.accept(request);

        if (response.getStatus() == FxDealStatus.ACCEPTED) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else if (response.getStatus() == FxDealStatus.NOT_ACCEPTED) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

