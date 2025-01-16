package com.spm.repositories;

import com.spm.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeatureRepository extends JpaRepository<Feature, Integer> {
}
