package com.klyashtorny.graduation.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "registered"}, name = "menu_unique_restaurant_id_registered_idx")})
public class Menu extends AbstractNamedEntity {

    @Column(name = "registered", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    protected List<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, String name) {
        super(id, name);
    }

    public Menu(Integer id, LocalDate registered, String name) {
        super(id, name);
        this.registered = registered;
    }

    public LocalDate getDate() {
        return registered;
    }

    public void setDate(LocalDate dateTime) {
        this.registered = registered;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", registered=" + registered +
                ", name='" + name +
                '}';
    }
}
