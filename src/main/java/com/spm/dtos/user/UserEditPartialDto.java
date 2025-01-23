package com.spm.dtos.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UserEditPartialDto(@Nullable String username,
                                 @Nullable @Size(min = 2,max = 40) String name,
                                 @Nullable @Size(min = 2, max = 40) String surname,
                                 @Nullable @Min(value = 1) @Max(value = 130) Integer age) {
}
