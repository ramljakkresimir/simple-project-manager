package com.spm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "feature")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "featureid", nullable = false)
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
    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectid")
    @JsonIgnore
    private Project projectid;

}