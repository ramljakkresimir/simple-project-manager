package com.spm.repositories;

import com.spm.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    List<Feature> findByUserIsNull();

    List<Feature> findByUserIsNullAndProjectid_Id(Integer projectId);
}
