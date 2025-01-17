package com.spm.dtos.user;

public record UserViewDto(int id,
                          String username,
                          String name,
                          String surname,
                          int age) {
}
