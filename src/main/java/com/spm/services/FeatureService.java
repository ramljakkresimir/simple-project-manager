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
        Project project = projectRepository.findById(featureDto.projectId())
                .orElseThrow(() -> new ResourceNotFound("Project not found"));

        Feature feature = featureMapper.toEntity(featureDto, project);
        Feature savedFeature = featureRepository.save(feature);
        return featureMapper.toDto(savedFeature);
    }

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Optional<Feature> getFeatureById(Integer id) {
        return featureRepository.findById(id);
    }

    public FeatureViewDto updateFeature(Integer id, FeatureCreationDto updatedFeatureDto) {
        Project project = projectRepository.findById(updatedFeatureDto.projectId())
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

    public void deleteFeature(Integer id) {
        if (!featureRepository.existsById(id)) {
            throw new ResourceNotFound("Feature not found");
        }
        featureRepository.deleteById(id);
    }
}
