package com.spm.dtos;

import java.time.LocalDate;

public record FeatureCreationDto(
        String name,
        String description,
        LocalDate deadline,
        Integer projectId
) {}
