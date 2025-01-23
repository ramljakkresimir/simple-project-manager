package com.spm.dtos.feature;

import java.time.LocalDate;

public record FeatureViewDto(
        Integer id,
        String name,
        String description,
        LocalDate deadline,
        String projectName,
        LocalDate deliveryDate,
        Integer PersonDayEstimate,
        com.spm.models.FeatureStatus status
) {}