package com.spm.controllers;


import com.spm.dtos.user.UserCreationDto;
import com.spm.dtos.user.UserViewDto;
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


@GetMapping("")
    public ResponseEntity<List<UserViewDto>> getAllUsers() {

        List<UserViewDto> users = userService.getAllUsersViewDto();
        return ResponseEntity.ok().body(users);
}

@GetMapping("/{id}")
    public ResponseEntity<UserViewDto> getUserById(@PathVariable int id) {
    System.out.println(id);
    UserViewDto userViewDto = userService.getUserViewById(id);
    return ResponseEntity.ok().body(userViewDto);
}

@PostMapping("")
    public ResponseEntity<UserViewDto> createUser(@RequestBody UserCreationDto newUser) {

    UserProject user = userService.createNewUser(newUser);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).build();
}

}
