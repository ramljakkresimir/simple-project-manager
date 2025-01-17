package com.spm.mappers.equipment;

import com.spm.dtos.equipment.EquipmentRequestDTO;
import com.spm.dtos.equipment.EquipmentResponseDTO;
import com.spm.models.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public Equipment toEntity(EquipmentRequestDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setName(dto.getName());
        equipment.setPrice(dto.getPrice());
        equipment.setWeight(dto.getWeight());
        equipment.setQuantity(dto.getQuantity());
        return equipment;
    }

    public EquipmentResponseDTO toResponseDTO(Equipment equipment) {
        EquipmentResponseDTO dto = new EquipmentResponseDTO();
        dto.setId(equipment.getId().longValue());
        dto.setName(equipment.getName());
        dto.setPrice(equipment.getPrice());
        dto.setWeight(equipment.getWeight());
        dto.setQuantity(equipment.getQuantity());
        return dto;
    }
}
