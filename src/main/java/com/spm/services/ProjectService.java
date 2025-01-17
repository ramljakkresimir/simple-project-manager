package com.spm.services;

import com.spm.dtos.feature.FeatureViewDto;
import com.spm.exceptions.ResourceNotFound;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.models.Equipment;
import com.spm.models.Feature;
import com.spm.models.Project;
import com.spm.models.UserProject;
import com.spm.repositories.EquipmentRepository;
import com.spm.repositories.FeatureRepository;
import com.spm.repositories.ProjectRepository;
import com.spm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FeatureRepository featureRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    private final FeatureMapper featureMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, FeatureRepository featureRepository, FeatureService featureService, EquipmentRepository equipmentRepository, UserRepository userRepository, FeatureMapper featureMapper) {
        this.projectRepository = projectRepository;
        this.featureRepository = featureRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
        this.featureMapper = featureMapper;
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
    public List<FeatureViewDto> getFeaturesByProject(Integer projectId) {
        Project project = projectRepository.findById(Long.valueOf(projectId))
                .orElseThrow(() -> new ResourceNotFound("Project not found with id " + projectId));

        return project.getFeatures().stream()
                .map(featureMapper::toDto)
                .toList();
    }

    public void deleteProject(Integer id){
        if(!projectRepository.existsById(Long.valueOf(id))){
            throw new RuntimeException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(Long.valueOf(id));
    }

}
