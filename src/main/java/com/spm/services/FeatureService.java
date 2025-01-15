package com.spm.services;

import com.spm.models.Feature;
import com.spm.repositories.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature createFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Optional<Feature> getFeatureById(Integer id) {
        return featureRepository.findById(id);
    }

    public Feature updateFeature(Integer id, Feature updateFeature) {
        return featureRepository.findById(id)
                .map(feature -> {
                    feature.setName(updatedFeature.getName());
                    feature.setDescription(updatedFeature.getDescription());
                    feature.setDeadline(updatedFeature.getDeadline());
                    feature.setProject(updatedFeature.getProject());
                    return featureRepository.save(feature);
                }).orElseThrow(() -> new ResourceNotFoundException("Feature not found"));
    }
}
