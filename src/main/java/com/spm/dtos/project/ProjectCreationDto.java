package com.spm.dtos.project;

import java.time.LocalDate;

public record ProjectCreationDto (String name,
                                  String description,
                                  Double budget,
                                  LocalDate deadline){
}
