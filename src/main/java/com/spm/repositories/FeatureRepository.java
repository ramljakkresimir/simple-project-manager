package com.spm.repositories;

import com.spm.dtos.user.UserFeaturesCountDto;
import com.spm.models.Feature;
import com.spm.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    @Query("SELECT new com.spm.dtos.user.UserFeaturesCountDto(f.user.id, COUNT(f))" +
            "FROM Feature f " +
            "WHERE f.projectid = :project " +
            "GROUP BY f.user.id")
    List<UserFeaturesCountDto> countFeaturesByUsersInProject(Project project);

    List<Feature> findAllByProjectid(Project projectId);
}
