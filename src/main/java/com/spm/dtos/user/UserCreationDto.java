package com.spm.dtos.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreationDto(@NotNull  String username,
                              @NotNull @Size(min = 2,max = 40) String name,
                              @NotNull @Size(min = 2, max = 40) String surname,
                              @NotNull @Min(value = 1) @Max(value = 130) int age) {
}
