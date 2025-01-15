package com.spm.services;

import com.spm.exceptions.ResourceNotFound;
import com.spm.models.Feature;
import com.spm.repositories.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;
    //private final ProjectRepository projectRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
        //this.projectRepository = projectRepository;
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

    public Feature updateFeature(Integer id, Feature updatedFeature) {
        return featureRepository.findById(id)
                .map(feature -> {
                    feature.setName(updatedFeature.getName());
                    feature.setDescription(updatedFeature.getDescription());
                    feature.setDeadline(updatedFeature.getDeadline());
                    feature.setProjectid(updatedFeature.getProjectid());
                    return featureRepository.save(feature);
                }).orElseThrow(() -> new ResourceNotFound("Feature not found"));
    }
    /*public FeatureViewDto createFeature(FeatureCreationDto featureDto) {
        Project project = projectRepository.findById(featureDto.projectId())
                .orElseThrow(() -> new NoSuchElementException("Project not found"));

        Feature feature = FeatureMapper.featureCreationToFeature(featureDto, project);
        Feature savedFeature = featureRepository.save(feature);
        return FeatureMapper.featureToFeatureViewDto(savedFeature);
    }*/
    public void deleteFeature(Integer id) {
        featureRepository.deleteById(id);
    }
}
