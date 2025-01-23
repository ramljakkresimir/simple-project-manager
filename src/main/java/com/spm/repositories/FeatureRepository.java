package com.spm.repositories;

import com.spm.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    @Query("SELECT new com.spm.dtos.user.UserFeaturesCountDto(f.user.id, COUNT(f))" +
            "FROM Feature f " +
            "WHERE f.projectid = :project " +
            "GROUP BY f.user.id")
    List<UserFeaturesCountDto> countFeaturesByUsersInProject(Project project);

    List<Feature> findAllByProjectid(Project projectId);
    @Query("SELECT f FROM Feature f WHERE f.deliveryDate IS NOT NULL AND f.deliveryDate BETWEEN :startDate AND :endDate")
    List<Feature> findDeliveredFeaturesInTimePeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Feature> findByUserIsNull();

    List<Feature> findByUserIsNullAndProjectid_Id(Integer projectId);
}
