package com.klyashtorny.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurant_unique_user_name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address", nullable = false)
    private String Address;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                ", Address=" + Address +
                ", registered=" + registered +
                '}';
    }
}
