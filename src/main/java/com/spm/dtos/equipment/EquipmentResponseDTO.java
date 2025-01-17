package com.spm.dtos.equipment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Double weight;
    private int quantity;
}
