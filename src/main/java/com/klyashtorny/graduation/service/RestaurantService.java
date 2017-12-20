package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Restaurant get(int id, int userId) throws NotFoundException;

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    List<Restaurant> getAllByUser(int userId);

    List<Restaurant> getAll();
}
