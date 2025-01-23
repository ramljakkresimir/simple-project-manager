package com.spm.dtos.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public record UserEditDto( String username,
                           @Size(min = 2,max = 40) String name,
                           @Size(min = 2, max = 40) String surname,
                           @Min(value = 1) @Max(value = 130) Integer age) {
}
