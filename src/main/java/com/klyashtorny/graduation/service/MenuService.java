package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu create(Menu menu, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Menu get(int id, int restaurantId) throws NotFoundException;

    Menu getActualWithDishes(int id, int restaurantId, LocalDate date) throws NotFoundException;

    Menu update(Menu menu, int restaurantId) throws NotFoundException;

    List<Menu> getAllByRestaurant(int restaurantId);
}
