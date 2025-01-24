package com.spm.dtos.project;

import java.time.LocalDate;

public record ProjectViewDto(Integer id,
                             String name,
                             String description,
                             Double budget,
                             LocalDate deadline) {
}

