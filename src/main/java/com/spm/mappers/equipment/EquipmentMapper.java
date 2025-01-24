package com.spm.mappers.equipment;

import com.spm.dtos.equipment.EquipmentRequestDTO;
import com.spm.dtos.equipment.EquipmentResponseDTO;
import com.spm.models.Equipment;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public Equipment toEntity(EquipmentRequestDTO dto) {
        Equipment equipment = new Equipment();
        equipment.setId(null);
        equipment.setName(dto.name());
        equipment.setPrice(dto.price());
        equipment.setWeight(dto.weight());
        equipment.setQuantity(dto.quantity());
        return equipment;
    }

    public EquipmentResponseDTO toResponseDTO(Equipment equipment) {
        return new EquipmentResponseDTO(
                equipment.getId(),
                equipment.getName(),
                equipment.getPrice(),
                equipment.getWeight(),
                equipment.getQuantity()
        );
    }
}
