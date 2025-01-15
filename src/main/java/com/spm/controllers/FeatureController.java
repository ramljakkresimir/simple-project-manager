package com.spm.controllers;

import com.spm.dtos.FeatureCreationDto;
import com.spm.dtos.FeatureViewDto;
import com.spm.models.Feature;
import com.spm.services.FeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FeatureController {
    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }
    @PostMapping
    public ResponseEntity<FeatureViewDto> createFeature(@RequestBody FeatureCreationDto featureDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(featureService.createFeature(featureDto));
    }

    @GetMapping
    public List<Feature> getAllFeatures() {
        return featureService.getAllFeatures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Integer id) {
        return featureService.getFeatureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Integer id, @RequestBody Feature feature) {
        return new ResponseEntity<>(featureService.updateFeature(id, feature), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Integer id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
