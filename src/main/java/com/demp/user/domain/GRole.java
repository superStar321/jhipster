package com.demp.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GRole.
 */
@Entity
@Table(name = "g_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("gRoles")
    private GuestUser guestUser;

    @ManyToOne
    @JsonIgnoreProperties("gRoles")
    private GuestRole guestRole;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestUser getGuestUser() {
        return guestUser;
    }

    public GRole guestUser(GuestUser guestUser) {
        this.guestUser = guestUser;
        return this;
    }

    public void setGuestUser(GuestUser guestUser) {
        this.guestUser = guestUser;
    }

    public GuestRole getGuestRole() {
        return guestRole;
    }

    public GRole guestRole(GuestRole guestRole) {
        this.guestRole = guestRole;
        return this;
    }

    public void setGuestRole(GuestRole guestRole) {
        this.guestRole = guestRole;
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
        GRole gRole = (GRole) o;
        if (gRole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gRole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GRole{" +
            "id=" + getId() +
            "}";
    }
}
