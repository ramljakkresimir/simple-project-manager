package com.spm.services;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.exceptions.ResourceNotFound;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;
    private final FeatureMapper featureMapper;

    public FeatureService(FeatureRepository featureRepository, ProjectRepository projectRepository, FeatureMapper featureMapper) {
        this.featureRepository = featureRepository;
        this.projectRepository = projectRepository;
        this.featureMapper = featureMapper;
    }

    public FeatureViewDto createFeature(FeatureCreationDto featureDto) {
        Project project = projectRepository.findById(Long.valueOf(featureDto.projectId()))
                .orElseThrow(() -> new ResourceNotFound("Project not found"));

        Feature feature = featureMapper.toEntity(featureDto, project);
        Feature savedFeature = featureRepository.save(feature);
        return featureMapper.toDto(savedFeature);
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Feature getFeatureById(Integer id) {
        return featureRepository.findById(id)
                .orElse(null);
    }

    public FeatureViewDto updateFeature(Integer id, FeatureCreationDto updatedFeatureDto) {
        Project project = projectRepository.findById(Long.valueOf(updatedFeatureDto.projectId()))
                .orElseThrow(() -> new ResourceNotFound("Project not found"));

        Feature updatedFeature = featureRepository.findById(id)
                .map(feature -> {
                    feature.setName(updatedFeatureDto.name());
                    feature.setDescription(updatedFeatureDto.description());
                    feature.setDeadline(updatedFeatureDto.deadline());
                    feature.setProjectid(project);
                    return featureRepository.save(feature);
                }).orElseThrow(() -> new ResourceNotFound("Feature not found"));

        return featureMapper.toDto(updatedFeature);
    }

    public Feature markDeliveryDate(Integer featureId, LocalDate deliveryDate){
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found with ID: " + featureId));

        feature.setDeliveryDate(deliveryDate);

        return featureRepository.save(feature);
    }

    public List<Feature> getFeaturesDeliveredInTimePeriod(LocalDate startDate, LocalDate endDate) {
        return featureRepository.findDeliveredFeaturesInTimePeriod(startDate, endDate);
    }


    public void deleteFeature(Integer id) {
        if (!featureRepository.existsById(id)) {
            throw new ResourceNotFound("Feature not found");
        }
        featureRepository.deleteById(id);
    }
}
