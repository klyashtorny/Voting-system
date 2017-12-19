package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }
    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public void update(Restaurant restaurant) {

    }

    public List<Restaurant> getAll(int userId) {
        return repository.getRestaurantByUserIdOrderByName(userId);
    }
}
