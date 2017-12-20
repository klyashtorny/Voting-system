package com.klyashtorny.graduation.repository;

import com.klyashtorny.graduation.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    int deleteByIdAndUserId(int id, int userId);

    Restaurant getByIdAndUserId(int id, int userId);

    List<Restaurant> getByUserIdOrderByName(int id);

    List<Restaurant> findAll(Sort sort);

    @Override
    @Transactional
    Restaurant save(Restaurant item);

}
