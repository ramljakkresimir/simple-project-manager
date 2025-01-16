package com.spm.services;

import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserEditDto;
import com.spm.models.UserProject;

import java.util.List;

public interface IUserService {

     List<UserProject> getAllUsers();
     UserProject getUserById(int id);
     UserProject updateUser(int id, UserEditDto userEditDto);
     void deleteUserById(int id);
     UserProject createNewUser(UserCreationDto userCreationDto);

}
