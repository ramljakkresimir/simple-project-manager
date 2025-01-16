package com.spm.services;

import com.spm.models.Project;
import com.spm.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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

    public void deleteProject(Integer id){
        if(!projectRepository.existsById(Long.valueOf(id))){
            throw new RuntimeException("Project not found with ID: " + id);
        }
        projectRepository.deleteById(Long.valueOf(id));
    }

}
