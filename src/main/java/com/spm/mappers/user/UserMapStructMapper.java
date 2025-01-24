package com.spm.mappers.user;


import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserEditPartialDto;
import com.spm.dtos.user.UserViewDto;
import com.spm.models.UserProject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserMapStructMapper {
    UserMapStructMapper INSTANCE = Mappers.getMapper(UserMapStructMapper.class);

    void updateUserFromDto(UserEditPartialDto dto, @MappingTarget UserProject user);
    void  userToUserViewDto(UserProject user,@MappingTarget UserViewDto userViewDto);
    void userCreationToUserProject(UserCreationDto userDto,@MappingTarget UserProject userProject);
}
