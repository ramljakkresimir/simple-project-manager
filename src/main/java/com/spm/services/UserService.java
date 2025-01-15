package com.spm.services;

import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserViewDto;
import com.spm.mappers.user.UserMapper;
import com.spm.models.UserProject;
import com.spm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public List<UserViewDto> getAllUsersViewDto(){

        List<UserProject> users = userRepository.findAll();
        return users.stream().map(UserMapper::userToUserViewDto).collect(Collectors.toList());
    }

    public UserProject createNewUser(UserCreationDto newUser) {
        return userRepository.save(UserMapper.userCreationToUserProject(newUser));
    }

    public UserViewDto getUserViewById(int id) {
        Optional<UserProject> user = userRepository.findById(id);
        return user.map(UserMapper::userToUserViewDto).orElse(null);
    }
}
