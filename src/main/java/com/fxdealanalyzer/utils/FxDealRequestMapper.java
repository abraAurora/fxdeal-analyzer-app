package com.fxdealanalyzer.utils;

import com.fxdealanalyzer.repository.entity.FxDealEntity;
import com.fxdealanalyzer.module.FxDealRequest;
import org.mapstruct.Mapper;

@Mapper
public interface FxDealRequestMapper {
    FxDealEntity toEntity(FxDealRequest request);
}