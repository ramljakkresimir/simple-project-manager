package com.spm.mappers;

import com.spm.dtos.FeatureCreationDto;
import com.spm.dtos.FeatureViewDto;
import com.spm.models.Feature;
import com.spm.models.Project;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    public static FeatureViewDto featureToFeatureViewDto(Feature feature) {
        return new FeatureViewDto(
                feature.getId(),
                feature.getName(),
                feature.getDescription(),
                feature.getDeadline(),
                //feature.getProject().getName()
        );
    }

    public static Feature featureCreationToFeature(FeatureCreationDto featureDto, Project project) {
        Feature feature = new Feature();
        feature.setId(null);
        feature.setName(featureDto.name());
        feature.setDescription(featureDto.description());
        feature.setDeadline(featureDto.deadline());
        //feature.setProject(project);
        return feature;
    }
}
