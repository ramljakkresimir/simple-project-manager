package com.spm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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

    @PastOrPresent(message = "Delivery date cannot be in the future.")
    @Column(name = "delivery_date", nullable = true)
    private LocalDate deliveryDate;

    @Min(value = 0, message = "Person-day estimate cannot be negative.")
    @Column(name = "person_day_estimate", nullable = true)
    private Integer personDayEstimate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",length = 40, nullable = true)
    //default je not started
    private FeatureStatus status = FeatureStatus.NOT_STARTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectid")
    @JsonIgnore
    private Project projectid;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnore
    private UserProject user;

}