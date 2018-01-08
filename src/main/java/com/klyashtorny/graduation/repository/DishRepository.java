package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    int deleteByIdAndMenuId(int id, int menuId);

    @Override
    @Transactional
    Dish save(Dish item);

    Dish getByIdAndMenuId(int id, int menuId);

    List<Dish> getByMenuIdOrderByName(int menuId);
}
