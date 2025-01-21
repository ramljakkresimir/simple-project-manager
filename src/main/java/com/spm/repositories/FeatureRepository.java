package com.spm.repositories;

import com.spm.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    List<Feature> findByClaimedByIsNull();

    List<Feature> findByClaimedByIsNullAndProjectid_Id(Integer projectId);
}
