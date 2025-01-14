package com.spm.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_equipment")
public class ProjectEquipment {
    @EmbeddedId
    private ProjectEquipmentId id;

    @MapsId("projectid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projectid", nullable = false)
    private Project projectid;

    @MapsId("eqid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eqid", nullable = false)
    private Equipment eqid;

}