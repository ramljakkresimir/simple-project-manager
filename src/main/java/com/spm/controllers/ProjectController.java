package com.spm.controllers;

import com.spm.dtos.feature.FeatureViewDto;
import com.spm.dtos.user.UserFeaturesCountDto;
import com.spm.models.*;
import com.spm.dtos.project.ProjectBudgetSummaryDTO;
import com.spm.models.Equipment;
import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.models.UserProject;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.services.FeatureService;
import com.spm.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final FeatureService featureService;

    public ProjectController(ProjectService projectService, FeatureService featureService) {
        this.projectService = projectService;
        this.featureService = featureService;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id) {
            Project project = projectService.getProjectById(id);
            return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/assignFeature")
    public ResponseEntity<Feature> addFeatureToProject(@PathVariable Integer id, @RequestBody Integer featureId) {
        Feature addedFeature = projectService.addFeature(id, featureId);

        if (addedFeature != null) {
            return new ResponseEntity<>(addedFeature, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/assignEquipment")
    public ResponseEntity<Equipment> addEquipmentToProject(@PathVariable Integer id, @RequestBody Integer equipmentId) {
        Equipment addedEquipment = projectService.addEquipment(id, equipmentId);

        if (addedEquipment != null) {
            return new ResponseEntity<>(addedEquipment, HttpStatus.OK); //return the added equipment
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/assignUser")
    public ResponseEntity<UserProject> addUserToProject(@PathVariable Integer id, @RequestBody Integer userId) {
        UserProject addedUser = projectService.addUser(id, userId);

        if (addedUser != null) {
            return new ResponseEntity<>(addedUser, HttpStatus.OK); //return the added user
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer id, @RequestBody Project project){
        Project updatedProject = projectService.updateProject(id, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build(); //return 204 - no content on successful deletion
    }

    @GetMapping("/{id}/equipment")
    public ResponseEntity<List<Equipment>> getEquipmentByProjectId(@PathVariable Long id) {
            List<Equipment> equipmentList = projectService.getAllEquipmentByProjectId(id);
            return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @GetMapping("{id}/users")
    public ResponseEntity<List<UserProject>> getUsersByProjectId(@PathVariable Long id) {
            List<UserProject> userList = projectService.getAllUsersByProjectId(id);
            return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}/features")
    public ResponseEntity<List<FeatureViewDto>> getFeaturesByProject(@PathVariable Integer id) {
        List<FeatureViewDto> featureDtos = projectService.getFeaturesByProject(id);
        return ResponseEntity.ok(featureDtos);
    }

    @GetMapping("/{projectId}/features/group-by-user")
    public ResponseEntity<List<UserFeaturesCountDto>> sumOfFeaturesPerUserInProject(@PathVariable int projectId) {
        return ResponseEntity.ok(featureService.getFeaturesSumByUser(projectId));
    }

    @GetMapping("/{projectId}/features/count/group-by-status")
    public ResponseEntity<Map<FeatureStatus,List<Feature>>> featuresByStatusInProject(@PathVariable int projectId) {
        return ResponseEntity.ok(featureService.getFeaturesByStatus(projectId));
    }

    ///@GetMapping("/exceeded-budget")
    ///public ResponseEntity<List<ProjectBudgetSummaryDTO>> getProjectsExceedingBudget() {
    ///    List<ProjectBudgetSummaryDTO> projects = projectService.getProjectBudgetSummary();
    ///    return new ResponseEntity<>(projects, HttpStatus.OK);
   /// }

    @GetMapping("/budget-summary")
    public ResponseEntity<List<ProjectBudgetSummaryDTO>> getBudgetSummaries(
            @RequestParam(required = false) Boolean exceededBudget, // Filter by exceeded budget
            @RequestParam(defaultValue = "id") String sortBy, // Sorting field
            @RequestParam(defaultValue = "asc") String order, // Sorting order
            @RequestParam(defaultValue = "0") int page, // Page number
            @RequestParam(defaultValue = "10") int size // Page size
    ) {
        List<ProjectBudgetSummaryDTO> budgetSummaries = projectService.getFilteredBudgetSummaries(
                exceededBudget, sortBy, order, page, size
        );
        return new ResponseEntity<>(budgetSummaries, HttpStatus.OK);
    }
}
