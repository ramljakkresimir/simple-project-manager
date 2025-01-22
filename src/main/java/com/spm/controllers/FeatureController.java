package com.spm.controllers;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.services.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return featureService.getFeatureById(id)
                .map(featureMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureViewDto> updateFeature(@PathVariable Integer id, @RequestBody FeatureCreationDto featureDto) {
        System.out.println(featureDto);
        FeatureViewDto updatedFeature = featureService.updateFeature(id, featureDto);
        return ResponseEntity.ok(updatedFeature);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Integer id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
