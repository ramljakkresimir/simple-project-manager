package com.spm.dtos.equipment;

public record EquipmentResponseDTO(
        int id,
        String name,
        Double price,
        Double weight,
        int quantity
) {
}
