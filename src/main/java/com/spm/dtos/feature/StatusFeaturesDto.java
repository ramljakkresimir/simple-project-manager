package com.spm.dtos.feature;

import com.spm.models.Feature;
import com.spm.models.FeatureStatus;

import java.util.List;

public record StatusFeaturesDto(
        FeatureStatus status,
        List<Feature> features
) {
}
