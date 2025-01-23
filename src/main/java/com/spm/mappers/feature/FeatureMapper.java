package com.spm.mappers.feature;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.models.Feature;
import com.spm.models.FeatureStatus;
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
        feature.setPersonDayEstimate(featureCreationDto.PersonDayEstimate());
        feature.setStatus(FeatureStatus.valueOf(featureCreationDto.status()));
        return feature;
    }

    public FeatureViewDto toDto(Feature feature) {
        return new FeatureViewDto(
                feature.getId(),
                feature.getName(),
                feature.getDescription(),
                feature.getDeadline(),
                feature.getProjectid() != null ? feature.getProjectid().getName() : null,
                feature.getDeliveryDate(),
                feature.getPersonDayEstimate(),
                feature.getStatus()
        );
    }
}
