package com.spm.mappers.feature;


import com.spm.dtos.feature.FeatureUpdateDto;
import com.spm.models.Feature;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FeatureMapStructMapper {

     FeatureMapStructMapper INSTANCE = Mappers.getMapper(FeatureMapStructMapper.class);

     void partialUpdateFeatureFromDto(FeatureUpdateDto featureUpdateDto,@MappingTarget Feature feature);
}
