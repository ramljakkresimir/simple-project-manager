package com.spm.controllers;

import com.spm.dtos.feature.FeatureViewDto;
import com.spm.dtos.user.UserFeaturesCountDto;
import com.spm.models.*;
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

    @PostMapping("/{id}/features")
    public ResponseEntity<Feature> addFeatureToProject(@PathVariable Integer id, @RequestBody Integer featureId) {
        Feature addedFeature = projectService.addFeature(id, featureId);

        if (addedFeature != null) {
            return new ResponseEntity<>(addedFeature, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/equipment")
    public ResponseEntity<Equipment> addEquipmentToProject(@PathVariable Integer id, @RequestBody Integer equipmentId) {
        Equipment addedEquipment = projectService.addEquipment(id, equipmentId);
        System.out.println(id);
        System.out.println(equipmentId);

        if (addedEquipment != null) {
            return new ResponseEntity<>(addedEquipment, HttpStatus.OK); //return the added equipment
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<UserProject> addUserToProject(@PathVariable Integer id, @RequestBody Integer userId) {
        UserProject addedUser = projectService.addUser(id, userId);
        System.out.println(id);
        System.out.println(userId);

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

}
