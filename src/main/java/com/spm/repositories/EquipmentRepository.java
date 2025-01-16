package com.spm.repositories;

import com.spm.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query("SELECT e FROM Equipment e JOIN e.projects p WHERE p.id = ?1")
    List<Equipment> findByProjectId(Long projectId);


}
