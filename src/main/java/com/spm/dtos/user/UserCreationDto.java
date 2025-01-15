package com.spm.dtos.user;

public record UserCreationDto(String username,
                              String name,
                              String surname,
                              int age) {
}
