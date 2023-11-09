package com.fxdealanalyzer.repository;

import com.fxdealanalyzer.repository.entity.FxDealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FxDealRepository extends JpaRepository<FxDealEntity, Long> {

    Optional<FxDealEntity> findByDealUniqueId(String dealUniqueId);
}
