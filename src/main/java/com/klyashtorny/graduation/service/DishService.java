package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Dish;
import com.klyashtorny.graduation.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish create(Dish dish, int menuId);

    void delete(int id, int menuId) throws NotFoundException;

    Dish get(int id, int menuId) throws NotFoundException;

    Dish update(Dish dish, int menuId) throws NotFoundException;

    List<Dish> getAllByMenu(int menuId);
}
