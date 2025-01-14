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
public class ProjectUserId implements Serializable {
    private static final long serialVersionUID = 3218360428862045079L;
    @Column(name = "projectid", nullable = false)
    private Integer projectid;

    @Column(name = "userid", nullable = false)
    private Integer userid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectUserId entity = (ProjectUserId) o;
        return Objects.equals(this.projectid, entity.projectid) &&
                Objects.equals(this.userid, entity.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectid, userid);
    }

}