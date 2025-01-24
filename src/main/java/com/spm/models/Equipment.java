package com.spm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eqid", nullable = false)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Double weight;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_equipment",
            joinColumns = @JoinColumn(name = "eqid", referencedColumnName = "eqid"),
            inverseJoinColumns = @JoinColumn(name = "projectid", referencedColumnName = "projectid"))
    @JsonIgnore
    private Set<Project> projects = new LinkedHashSet<>();

}