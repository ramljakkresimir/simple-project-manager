package com.spm.dtos.project;

public record ProjectBudgetSummaryDTO(
        Long id,
        String name,
        Double budget,
        Double totalEquipmentCost,
        Boolean exceededBudget
) {
}
