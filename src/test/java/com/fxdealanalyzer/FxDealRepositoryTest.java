package com.fxdealanalyzer;

import com.fxdealanalyzer.repository.FxDealRepository;
import com.fxdealanalyzer.repository.entity.FxDealEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FxDealRepositoryTest {

    @Autowired
    private FxDealRepository fxDealRepository;

    @Test
    void testFindByDealUniqueId() {
        FxDealEntity fxDealEntity = new FxDealEntity();
        fxDealEntity.setDealUniqueId("sampleUniqueId");
        fxDealRepository.save(fxDealEntity);

        Optional<FxDealEntity> foundEntityOptional = fxDealRepository.findByDealUniqueId("sampleUniqueId");

        assertTrue(foundEntityOptional.isPresent());

        FxDealEntity foundEntity = foundEntityOptional.get();

        assertEquals("sampleUniqueId", foundEntity.getDealUniqueId());
    }

    @Test
    void testFindByDealUniqueId_NotFound() {
        Optional<FxDealEntity> foundEntityOptional = fxDealRepository.findByDealUniqueId("nonExistentUniqueId");

        assertFalse(foundEntityOptional.isPresent());
    }
}
