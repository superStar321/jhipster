package com.demp.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_name")
    private String carName;

    @Column(name = "car_brand")
    private String carBrand;

    @ManyToOne
    @JsonIgnoreProperties("cars")
    private GuestUser guestUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public Car carName(String carName) {
        this.carName = carName;
        return this;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public Car carBrand(String carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public GuestUser getGuestUser() {
        return guestUser;
    }

    public Car guestUser(GuestUser guestUser) {
        this.guestUser = guestUser;
        return this;
    }

    public void setGuestUser(GuestUser guestUser) {
        this.guestUser = guestUser;
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
        Car car = (Car) o;
        if (car.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + getId() +
            ", carName='" + getCarName() + "'" +
            ", carBrand='" + getCarBrand() + "'" +
            "}";
    }
}
