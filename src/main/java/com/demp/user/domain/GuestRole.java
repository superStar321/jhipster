package com.demp.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A GuestRole.
 */
@Entity
@Table(name = "guest_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GuestRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "guestRole")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GRole> gRoles = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public GuestRole roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<GRole> getGRoles() {
        return gRoles;
    }

    public GuestRole gRoles(Set<GRole> gRoles) {
        this.gRoles = gRoles;
        return this;
    }

    public GuestRole addGRole(GRole gRole) {
        this.gRoles.add(gRole);
        gRole.setGuestRole(this);
        return this;
    }

    public GuestRole removeGRole(GRole gRole) {
        this.gRoles.remove(gRole);
        gRole.setGuestRole(null);
        return this;
    }

    public void setGRoles(Set<GRole> gRoles) {
        this.gRoles = gRoles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuestRole guestRole = (GuestRole) o;
        if (guestRole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), guestRole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GuestRole{" +
            "id=" + getId() +
            ", roleName='" + getRoleName() + "'" +
            "}";
    }
}
