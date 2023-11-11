package com.fxdealanalyzer.repository;

import com.fxdealanalyzer.repository.entity.FxDealEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface FxDealRepository extends JpaRepository<FxDealEntity, Long> {

    Optional<FxDealEntity> findByDealUniqueId(String dealUniqueId);
}
