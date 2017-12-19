package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    void update(Restaurant restaurant);

    List<Restaurant> getAll(int userId);
}
