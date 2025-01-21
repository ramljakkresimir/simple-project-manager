package com.spm.controllers;

import com.spm.models.Feature;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.mappers.feature.FeatureMapper;
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

    @PutMapping("/{id}")
    public ResponseEntity<FeatureViewDto> updateFeature(@PathVariable Integer id, @RequestBody FeatureCreationDto featureDto) {
        FeatureViewDto updatedFeature = featureService.updateFeature(id, featureDto);
        return ResponseEntity.ok(updatedFeature);
    }

    @PatchMapping("/{id}/delivery-date")
    public ResponseEntity<Feature> markDeliveryDate(@PathVariable Integer id, @RequestBody LocalDate deliveryDate){
        try{
            Feature updatedFeature = featureService.markDeliveryDate(id, deliveryDate);
            return  ResponseEntity.ok(updatedFeature);
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Integer id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
