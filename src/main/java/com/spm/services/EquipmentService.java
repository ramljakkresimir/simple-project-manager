package com.spm.services;

import com.spm.exceptions.ResourceNotFound;
import com.spm.models.Equipment;
import com.spm.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class EquipmentService {


    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Equipment not found with id: " + id));
    }

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = getEquipmentById(id);
        equipment.setName(equipmentDetails.getName());
        equipment.setPrice(equipmentDetails.getPrice());
        equipment.setWeight(equipmentDetails.getWeight());
        equipment.setQuantity(equipmentDetails.getQuantity());
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> getEquipmentByProjectId(Long projectId) {
        return equipmentRepository.findByProjectId(projectId);
    }
}

