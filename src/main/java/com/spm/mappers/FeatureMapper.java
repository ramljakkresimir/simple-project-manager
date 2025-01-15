package com.spm.mappers;

import com.spm.dtos.FeatureCreationDto;
import com.spm.dtos.FeatureViewDto;
import com.spm.models.Feature;
import com.spm.models.Project;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    public Feature toEntity(FeatureCreationDto featureCreationDto, Project project) {
        Feature feature = new Feature();
        feature.setName(featureCreationDto.name());
        feature.setDescription(featureCreationDto.description());
        feature.setDeadline(featureCreationDto.deadline());
        feature.setProjectid(project);
        return feature;
    }

    public FeatureViewDto toDto(Feature feature) {
        return new FeatureViewDto(
                feature.getId(),
                feature.getName(),
                feature.getDescription(),
                feature.getDeadline(),
                feature.getProjectid() != null ? feature.getProjectid().getName() : null
        );
    }
}
