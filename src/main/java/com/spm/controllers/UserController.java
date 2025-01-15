package com.spm.controllers;


import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserEditDto;
import com.spm.dtos.user.UserViewDto;
import com.spm.mappers.user.UserMapper;
import com.spm.models.UserProject;
import com.spm.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

private final UserService userService;


@GetMapping
    public ResponseEntity<List<UserViewDto>> getAllUsers() {

        List<UserProject> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users.stream().map(UserMapper::userToUserViewDto).toList());
}

@GetMapping("/{id}")
    public ResponseEntity<UserViewDto> getUserById(@PathVariable int id) {
    System.out.println(id);
    UserProject userProject = userService.getUserById(id);
    return ResponseEntity.ok().body(UserMapper.userToUserViewDto(userProject));
}

@PostMapping
    public ResponseEntity<UserViewDto> createUser(@RequestBody UserCreationDto newUser) {

    UserProject user = userService.createNewUser(newUser);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).build();
}

@PutMapping("/{id}")
    public ResponseEntity<UserViewDto> updateUser(@PathVariable int id, @RequestBody UserEditDto updateUser) {
    UserProject user = userService.updateUser(id,updateUser);
    return ResponseEntity.ok().body(UserMapper.userToUserViewDto(user));

}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
}

}
