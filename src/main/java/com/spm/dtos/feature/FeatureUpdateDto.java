package com.spm.dtos.feature;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record FeatureUpdateDto(
        @Nullable String name,
        @Nullable String description,
        @Nullable LocalDate deadline,
        @Nullable Integer projectId,
        @Nullable @PastOrPresent(message = "Delivery date should be past or present.") LocalDate deliveryDate,
        @Nullable Integer personDayEstimate,
        @Nullable String status
) {}
