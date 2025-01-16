package com.spm.controllers;

import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.services.FeatureService;
import com.spm.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final FeatureService featureService;
    private final ProjectRepository projectRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public ProjectController(ProjectService projectService, FeatureService featureService,
                             ProjectRepository projectRepository,
                             FeatureRepository featureRepository) {
        this.projectService = projectService;
        this.featureService = featureService;
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
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

    //assigns existing feature to a project
    @PostMapping("/{id}/features")
    public ResponseEntity<Project> addFeatureToProject(@RequestBody Integer feature_id, @PathVariable Integer id){
        Optional<Feature> exsistingFeature = featureService.getFeatureById(feature_id);
        Optional<Project> existingProject = projectService.getProjectById(id);

        if(existingProject.isPresent() && exsistingFeature.isPresent()){
            System.out.println(id);
            System.out.println(feature_id);
            Project project = existingProject.get();
            Feature feature = exsistingFeature.get();

            feature.setProjectid(project);
            featureRepository.save(feature);
        }

        return ResponseEntity.noContent().build();

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

}
