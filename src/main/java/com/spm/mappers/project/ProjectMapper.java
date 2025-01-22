package com.spm.mappers.project;

import com.spm.dtos.project.ProjectBudgetSummaryDTO;
import com.spm.dtos.project.ProjectCreationDto;
import com.spm.dtos.project.ProjectViewDto;
import com.spm.models.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    //convert Entity to ProjectViewDto
    public static ProjectViewDto projectToProjectViewDto(Project project){
        return new ProjectViewDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getBudget()
        );
    }

    //convert ProjectCreationDto to Entity
    public Project toEntity(ProjectCreationDto creationDto){
        Project project = new Project();
        project.setName((creationDto.name()));
        project.setDescription(creationDto.description());
        project.setBudget((creationDto.budget()));

        return project;
    }

    public ProjectBudgetSummaryDTO toBudgetSummaryDTO(Project project) {
        Double totalEquipmentCost = project.getEquipment().stream()
                .mapToDouble(equipment -> equipment.getPrice() * equipment.getQuantity())
                .sum();

        boolean exceededBudget = totalEquipmentCost > project.getBudget();

        return new ProjectBudgetSummaryDTO(
                project.getId().longValue(),
                project.getName(),
                project.getBudget(),
                totalEquipmentCost,
                exceededBudget
        );
    }

}
