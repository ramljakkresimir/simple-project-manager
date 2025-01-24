package com.spm.dtos.project;

import java.time.LocalDate;

public record ProjectBudgetSummaryDTO(
        Long id,
        String name,
        Double budget,
        Double totalEquipmentCost,
        Boolean exceededBudget,
        LocalDate deadline
) {
}
