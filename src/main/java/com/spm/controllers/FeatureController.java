package com.spm.controllers;

import com.spm.models.Feature;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.models.Feature;
import com.spm.services.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    private final FeatureService featureService;
    private final FeatureMapper featureMapper;

    public FeatureController(FeatureService featureService, FeatureMapper featureMapper) {
        this.featureService = featureService;
        this.featureMapper = featureMapper;
    }

    @PostMapping
    public ResponseEntity<FeatureViewDto> createFeature(@RequestBody FeatureCreationDto featureDto) {
        FeatureViewDto createdFeature = featureService.createFeature(featureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeature);
    }

    @GetMapping
    public ResponseEntity<List<FeatureViewDto>> getAllFeatures() {
        List<FeatureViewDto> featureDtos = featureService.getAllFeatures().stream()
                .map(featureMapper::toDto)
                .toList();
        return ResponseEntity.ok(featureDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureViewDto> getFeatureById(@PathVariable Integer id) {
        Feature feature = featureService.getFeatureById(id);

        if (feature != null) {
            return ResponseEntity.ok(featureMapper.toDto(feature)); //map and return the feature DTO
        } else {
            return ResponseEntity.notFound().build(); //return 404 if the feature is not found
        }
    }

    @GetMapping("/delivered") //example: delivered?startDate=2023-01-01&endDate=2025-12-31
    public ResponseEntity<List<Feature>> getDeliveredFeaturesWithinDateRange(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        try {
            List<Feature> features = featureService.getFeaturesDeliveredInTimePeriod(startDate, endDate);
            return ResponseEntity.ok(features);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureViewDto> updateFeature(
            @PathVariable Integer id,
            @RequestBody FeatureCreationDto featureDto) {
        FeatureViewDto updatedFeature = featureService.updateFeature(id, featureDto);
        return ResponseEntity.ok(updatedFeature);
    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<FeatureViewDto> claimFeature(@PathVariable Integer id, @RequestParam Integer userId ) {
        FeatureViewDto featureDto = featureService.claimFeature(id,userId);
        return ResponseEntity.ok(featureDto);
    }

    @GetMapping("/unclaimed")
    public ResponseEntity<List<FeatureViewDto>> unclaimedFeatures(@RequestParam(required = false) Integer projectId) {
        List<Feature> unclaimedFeatures = featureService.getUnclaimedFeatures(projectId);
        List<FeatureViewDto> featureDtos =unclaimedFeatures.stream()
                .map(featureMapper::toDto)
                .toList();
        return ResponseEntity.ok(featureDtos);

    }

    @PostMapping
    public ResponseEntity<FeatureViewDto> createFeature(@RequestBody FeatureCreationDto featureDto) {
        FeatureViewDto createdFeature = featureService.createFeature(featureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeature);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Feature> markDeliveryDate(@PathVariable Integer id, @RequestBody LocalDate deliveryDate){
        try{
            Feature updatedFeature = featureService.markDeliveryDate(id, deliveryDate);
            return  ResponseEntity.ok(updatedFeature);
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}/estimate")
    public ResponseEntity<Feature> setPersonDayEstimate(
            @PathVariable Integer id,
            @RequestBody Integer personDayEstimate){
        try{
            Feature updatedFeature = featureService.setPersonDayEstimate(id, personDayEstimate);
            return ResponseEntity.ok(updatedFeature);
        } catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Integer id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
