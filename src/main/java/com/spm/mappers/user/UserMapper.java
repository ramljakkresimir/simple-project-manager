package com.spm.mappers.user;

import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserViewDto;
import com.spm.models.UserProject;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserViewDto userToUserViewDto(UserProject user) {
        return new UserViewDto(user.getId(), user.getUsername(), user.getName(), user.getSurname(), user.getAge());
    }

    public static UserProject userCreationToUserProject(UserCreationDto userDto) {
        UserProject userProject = new UserProject();
        userProject.setId(null);
        userProject.setUsername(userDto.username());
        userProject.setName(userDto.name());
        userProject.setSurname(userDto.surname());
        userProject.setAge(userDto.age());
        return userProject;
    }

}
