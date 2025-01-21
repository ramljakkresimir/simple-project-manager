package com.spm.dtos.feature;

import java.time.LocalDate;

public record FeatureDateRangeDto(LocalDate startDate,
                                 LocalDate endDate)
{ }
