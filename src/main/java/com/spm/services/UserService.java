package com.spm.services;

import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserEditDto;
import com.spm.dtos.user.UserViewDto;
import com.spm.exceptions.ResourceNotFound;
import com.spm.mappers.user.UserMapper;
import com.spm.models.UserProject;
import com.spm.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public List<UserProject> getAllUsers(){
        return userRepository.findAll();
    }

    public UserProject createNewUser(UserCreationDto newUser) {
        return userRepository.save(UserMapper.userCreationToUserProject(newUser));
    }

    public UserProject getUserById(int id) {
        Optional<UserProject> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public UserProject updateUser(int id, UserEditDto updateUser) {
        UserProject user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Resurs nije pronađen"));
        user.setName(updateUser.name());
        user.setUsername(updateUser.username());
        user.setSurname(updateUser.surname());
        user.setAge(updateUser.age());
        return userRepository.save(user);
    }
}
