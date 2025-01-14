package com.spm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProjectEquipmentId implements Serializable {
    private static final long serialVersionUID = 3419427398449762913L;
    @Column(name = "projectid", nullable = false)
    private Integer projectid;

    @Column(name = "eqid", nullable = false)
    private Integer eqid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectEquipmentId entity = (ProjectEquipmentId) o;
        return Objects.equals(this.eqid, entity.eqid) &&
                Objects.equals(this.projectid, entity.projectid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eqid, projectid);
    }

}