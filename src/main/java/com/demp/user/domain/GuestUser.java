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
 * A GuestUser.
 */
@Entity
@Table(name = "guest_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GuestUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "guestUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Car> cars = new HashSet<>();
    @OneToMany(mappedBy = "guestUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<GRole> gRoles = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GuestUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public GuestUser phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public GuestUser cars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }

    public GuestUser addCar(Car car) {
        this.cars.add(car);
        car.setGuestUser(this);
        return this;
    }

    public GuestUser removeCar(Car car) {
        this.cars.remove(car);
        car.setGuestUser(null);
        return this;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<GRole> getGRoles() {
        return gRoles;
    }

    public GuestUser gRoles(Set<GRole> gRoles) {
        this.gRoles = gRoles;
        return this;
    }

    public GuestUser addGRole(GRole gRole) {
        this.gRoles.add(gRole);
        gRole.setGuestUser(this);
        return this;
    }

    public GuestUser removeGRole(GRole gRole) {
        this.gRoles.remove(gRole);
        gRole.setGuestUser(null);
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
        GuestUser guestUser = (GuestUser) o;
        if (guestUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), guestUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GuestUser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            "}";
    }
}
