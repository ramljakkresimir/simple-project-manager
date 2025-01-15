package com.spm.services;

import com.spm.models.Project;
import com.spm.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Integer id){
        return projectRepository.findById(Long.valueOf(id));
        //return Optional.ofNullable(projectRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("Project not found with ID: " + id)));
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }
}
