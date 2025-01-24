package com.spm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectid", nullable = false)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Size(max = 500)
    @NotNull
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @NotNull
    @Column(name = "budget", nullable = false)
    private Double budget;

    @NotNull
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @OneToMany(mappedBy = "projectid",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Feature> features = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "projects",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Equipment> equipment = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "projects",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserProject> users = new LinkedHashSet<>();

}