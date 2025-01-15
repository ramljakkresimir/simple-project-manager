package com.spm.dtos;

import java.time.LocalDate;

public record FeatureViewDto(
        Integer id,
        String name,
        String description,
        LocalDate deadline,
        String projectName
) {}