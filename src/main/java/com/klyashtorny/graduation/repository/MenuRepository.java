package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    int deleteByIdAndRestaurantId(int id, int restaurantId);

    Menu getByIdAndRestaurantId(int id, int restaurantId);

    List<Menu> getByRestaurantIdOrderByName(int restaurantId);

    @Override
    @Transactional
    Menu save(Menu item);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1 AND m.restaurant.id = ?2 AND m.registered >=?3")
    Optional<Menu> getWithDishes(int id, int restaurantId, LocalDate date);

}
