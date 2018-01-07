package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getByMenuId(int menuId);

}
