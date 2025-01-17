package com.spm.controllers;

import com.spm.dtos.equipment.EquipmentResponseDTO;
import com.spm.dtos.equipment.EquipmentRequestDTO;
import com.spm.mappers.equipment.EquipmentMapper;
import com.spm.models.Equipment;
import com.spm.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @GetMapping
    public List<EquipmentResponseDTO> getAllEquipment() {
        return equipmentService.getAllEquipment().stream()
                .map(equipmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EquipmentResponseDTO getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return equipmentMapper.toResponseDTO(equipment);
    }

    @PostMapping
    public EquipmentResponseDTO addEquipment(@RequestBody EquipmentRequestDTO equipmentRequestDTO) {
        Equipment equipment = equipmentMapper.toEntity(equipmentRequestDTO);
        Equipment savedEquipment = equipmentService.addEquipment(equipment);
        return equipmentMapper.toResponseDTO(savedEquipment);
    }

    @PutMapping("/{id}")
    public EquipmentResponseDTO updateEquipment(
            @PathVariable Long id, @RequestBody EquipmentRequestDTO equipmentRequestDTO) {
        Equipment equipmentDetails = equipmentMapper.toEntity(equipmentRequestDTO);
        Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDetails);
        return equipmentMapper.toResponseDTO(updatedEquipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public List<EquipmentResponseDTO> getEquipmentByProjectId(@PathVariable Long projectId) {
        return equipmentService.getEquipmentByProjectId(projectId).stream()
                .map(equipmentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
