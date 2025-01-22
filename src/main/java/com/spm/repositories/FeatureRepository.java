package com.spm.repositories;

import com.spm.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    @Query("SELECT f FROM Feature f WHERE f.deliveryDate IS NOT NULL AND f.deliveryDate BETWEEN :startDate AND :endDate")
    List<Feature> findDeliveredFeaturesInTimePeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
