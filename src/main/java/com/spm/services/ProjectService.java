package com.spm.services;

import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.models.Equipment;
import com.spm.models.UserProject;
import com.spm.repositories.EquipmentRepository;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.spm.services.FeatureService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FeatureRepository featureRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, FeatureRepository featureRepository, FeatureService featureService, EquipmentRepository equipmentRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "id")); //keep projects sorted by id
    }

    public Optional<Project> getProjectById(Integer id){
        return Optional.ofNullable(projectRepository.findById(Long.valueOf(id)).
                orElseThrow(() -> new RuntimeException("Project not found with ID: " + id)));
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project updatedProject){
        return projectRepository.findById(Long.valueOf(id))
                .map(existingProject -> {
                    existingProject.setName(updatedProject.getName());
                    existingProject.setDescription(updatedProject.getDescription());
                    existingProject.setBudget(updatedProject.getBudget());

                    //save updated project
                    return projectRepository.save(existingProject);
                }).orElseThrow(() -> new RuntimeException("Project not found with ID: " + id));
    }

    public Optional<Feature> addFeature(Integer projectId, Integer featureId) {
        Optional<Project> existingProject = projectRepository.findById(Long.valueOf(projectId));
        Optional<Feature> existingFeature = featureRepository.findById(featureId);

        if (existingProject.isPresent() && existingFeature.isPresent()) {
            Project project = existingProject.get();
            Feature feature = existingFeature.get();

            feature.setProjectid(project);
            Feature savedFeature = featureRepository.save(feature);
            return Optional.of(savedFeature);
        }

        return Optional.empty();
    }

    public Optional<Equipment> addEquipment(Integer projectId, Integer equipmentId) {
        Optional<Project> existingProject = projectRepository.findById(Long.valueOf(projectId));
        Optional<Equipment> existingEquipment = equipmentRepository.findById(Long.valueOf(equipmentId));

        if (existingProject.isPresent() && existingEquipment.isPresent()) {
            Project project = existingProject.get();
            Equipment equipment = existingEquipment.get();

            project.getEquipment().add(equipment);
            equipment.getProjects().add(project);
            projectRepository.save(project);
            equipmentRepository.save(equipment);

            return Optional.of(equipment);
        }

        return Optional.empty();
    }

    public Optional<UserProject> addUser(Integer projectId, Integer userId) {
        Optional<Project> existingProject = projectRepository.findById(Long.valueOf(projectId));
        Optional<UserProject> existingUser = userRepository.findById(userId);

        if (existingProject.isPresent() && existingUser.isPresent()) {
            Project project = existingProject.get();
            UserProject user = existingUser.get();
            project.getUsers().add(user);
            user.getProjects().add(project);

            projectRepository.save(project);
            userRepository.save(user);

            return Optional.of(user);
        }

        return Optional.empty();
    }

    public void deleteProject(Integer id){
        if(!projectRepository.existsById(Long.valueOf(id))){
            throw new RuntimeException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(Long.valueOf(id));
    }

    public List<Equipment> getAllEquipmentByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        return new ArrayList<>(project.getEquipment());
    }

    public List<UserProject> getAllUsersByProjectId(Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        return new ArrayList<>(project.getUsers());
    }
}
