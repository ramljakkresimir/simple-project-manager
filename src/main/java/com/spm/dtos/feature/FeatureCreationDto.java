package com.spm.dtos.feature;

import java.time.LocalDate;

public record FeatureCreationDto(
        String name,
        String description,
        LocalDate deadline,
        Integer projectId,
        LocalDate deliveryDate,
        Integer PersonDayEstimate
) {}
