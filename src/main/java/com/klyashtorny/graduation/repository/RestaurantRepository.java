package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> getRestaurantByUserIdOrderByName(int id);

}
