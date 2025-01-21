package com.spm.services;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.exceptions.ResourceNotFound;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.models.UserProject;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;
    private final FeatureMapper featureMapper;
    private final UserRepository userRepository;

    public FeatureService(FeatureRepository featureRepository, ProjectRepository projectRepository, FeatureMapper featureMapper,
                          UserRepository userRepository) {
        this.featureRepository = featureRepository;
        this.projectRepository = projectRepository;
        this.featureMapper = featureMapper;
        this.userRepository = userRepository;
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
                .orElseThrow(() -> new ResourceNotFound("Feature not found with id " + id));
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

    public FeatureViewDto claimFeature(Integer featureId, Integer userId){
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFound("Feature not found with id " + featureId));
        if(feature.getClaimedBy() != null) {
            throw new IllegalStateException("Feature is already claimed by another user");
        }

        UserProject user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));

        feature.setClaimedBy(user);
        Feature savedFeature = featureRepository.save(feature);
        return featureMapper.toDto(savedFeature);

    }

    public List<Feature> getUnclaimedFeatures(Integer projectId) {
        if(projectId != null) {
            return featureRepository.findByClaimedByIsNullAndProjectid_Id(projectId);
        }
        return featureRepository.findByClaimedByIsNull();
    }

    public void deleteFeature(Integer id) {
        if (!featureRepository.existsById(id)) {
            throw new ResourceNotFound("Feature not found");
        }
        featureRepository.deleteById(id);
    }
}
