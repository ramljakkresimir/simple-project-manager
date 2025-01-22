package com.spm.controllers;

import com.spm.dtos.feature.FeatureViewDto;
import com.spm.dtos.user.UserFeaturesCountDto;
import com.spm.models.*;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.services.FeatureService;
import com.spm.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final FeatureService featureService;
    private final FeatureRepository featureRepository;

    @Autowired
    public ProjectController(ProjectService projectService, FeatureService featureService,
                             ProjectRepository projectRepository,
                             FeatureRepository featureRepository, FeatureRepository featureRepository1) {
        this.projectService = projectService;
        this.featureService = featureService;
        this.featureRepository = featureRepository1;
    }

    @GetMapping
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Integer id){
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/features")
    public ResponseEntity<Feature> addFeatureToProject(@PathVariable Integer id, @RequestBody Integer featureId) {
        Optional<Feature> addedFeature = projectService.addFeature(id, featureId);
        return addedFeature.map(feature -> new ResponseEntity<>(feature, HttpStatus.OK)) //return feature
                .orElse(ResponseEntity.notFound().build()); //return 404
    }

    @PostMapping("/{id}/equipment")
    public ResponseEntity<Equipment> addEquipmentToProject(@PathVariable Integer id, @RequestBody Integer equipmentId) {
        Optional<Equipment> addedEquipment = projectService.addEquipment(id, equipmentId);
        System.out.println(id);
        System.out.println(equipmentId);
        return addedEquipment.map(equipment -> new ResponseEntity<>(equipment, HttpStatus.OK)) //return eq
                .orElse(ResponseEntity.notFound().build()); //return 404
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<UserProject> addUserToProject(@PathVariable Integer id, @RequestBody Integer userId) {
        Optional<UserProject> addedUser = projectService.addUser(id, userId);
        System.out.println(id);
        System.out.println(userId);
        return addedUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK)) //return user
                .orElse(ResponseEntity.notFound().build()); //return 404
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

    @GetMapping("/{id}/getEquipment")
    public ResponseEntity<List<Equipment>> getEquipmentByProjectId(@PathVariable Long id) {
        try {
            List<Equipment> equipmentList = projectService.getAllEquipmentByProjectId(id);
            return new ResponseEntity<>(equipmentList, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/getUsers")
    public ResponseEntity<List<UserProject>> getUsersByProjectId(@PathVariable Long id) {
        try {
            List<UserProject> userList = projectService.getAllUsersByProjectId(id);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
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
