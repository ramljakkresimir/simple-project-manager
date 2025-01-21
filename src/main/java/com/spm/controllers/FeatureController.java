package com.spm.controllers;

import com.spm.dtos.feature.FeatureCreationDto;
import com.spm.dtos.feature.FeatureViewDto;
import com.spm.mappers.feature.FeatureMapper;
import com.spm.models.Feature;
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
        Feature feature = featureService.getFeatureById(id);
        FeatureViewDto featureDto = featureMapper.toDto(feature);
        return ResponseEntity.ok(featureDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureViewDto> updateFeature(@PathVariable Integer id, @RequestBody FeatureCreationDto featureDto) {
        FeatureViewDto updatedFeature = featureService.updateFeature(id, featureDto);
        return ResponseEntity.ok(updatedFeature);
    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<FeatureViewDto> claimFeature(@PathVariable Integer id, @RequestParam Integer userId ) {
        FeatureViewDto featureDto = featureService.claimFeature(id,userId);
        return ResponseEntity.ok(featureDto);
    }

    @GetMapping("/unclaimed")
    public ResponseEntity<List<FeatureViewDto>> getUnclaimedFeatures(@RequestParam(required = false) Integer projectId) {
        List<Feature> unclaimedFeatures = featureService.getUnclaimedFeatures(projectId);
        List<FeatureViewDto> featureDtos =unclaimedFeatures.stream()
                .map(featureMapper::toDto)
                .toList();
        return ResponseEntity.ok(featureDtos);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Integer id) {
        featureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
