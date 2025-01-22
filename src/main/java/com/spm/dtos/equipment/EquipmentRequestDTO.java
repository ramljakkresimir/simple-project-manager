package com.spm.dtos.equipment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EquipmentRequestDTO(
        @NotNull(message = "Name is required")
        @Size(max = 40, message = "Name cannot exceed 40 characters")
        String name,

        @NotNull(message = "Price is required")
        @Min(value = 0, message = "Price must be greater than or equal to 0")
        Double price,

        @NotNull(message = "Weight is required")
        @Min(value = 0, message = "Weight must be greater than or equal to 0")
        Double weight,

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity must be greater than or equal to 0")
        int quantity
) {
}
