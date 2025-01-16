package com.spm.services;

import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserEditDto;
import com.spm.exceptions.ResourceNotFound;
import com.spm.mappers.user.UserMapper;
import com.spm.models.UserProject;
import com.spm.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    private UserRepository userRepository;


    public List<UserProject> getAllUsers(){
        return userRepository.findAll();
    }

    public UserProject createNewUser(UserCreationDto newUser) {
        return userRepository.save(UserMapper.userCreationToUserProject(newUser));
    }

    public UserProject getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFound("Resurs nije pronađen"));
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

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
