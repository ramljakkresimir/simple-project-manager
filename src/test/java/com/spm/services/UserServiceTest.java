package com.spm.services;

import com.spm.models.UserProject;
import com.spm.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;


    @Test
    void getAllUsers() {
        UserProject userProject1 = new UserProject();
        UserProject userProject2 = new UserProject();
        userProject1.setId(1);
        userProject1.setUsername("hsmontara");
        userProject1.setName("hrvoje");
        userProject1.setSurname("smontara");
        userProject1.setAge(23);
        userProject2.setId(2);
        userProject2.setUsername("ilevanic");
        userProject2.setName("ivan");
        userProject2.setSurname("levanic");
        userProject2.setAge(22);
        when(userRepository.findAll()).thenReturn(List.of(userProject1, userProject2));
        assertEquals(List.of(userProject1, userProject2), userService.getAllUsers());
    }

    @Test
    void createNewUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById() {
    }
}