package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Restaurant;
import com.klyashtorny.graduation.repository.RestaurantRepository;
import com.klyashtorny.graduation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.klyashtorny.graduation.util.ValidationUtil.*;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final RestaurantRepository repository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurant.setUser(userRepository.getOne(userId));
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.deleteByIdAndUserId(id, userId)!=0, id);
    }

    @Override
    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId (repository.getByIdAndUserId(id, userId), id);
    }

    @Override
    public Restaurant update(Restaurant restaurant,  int userId) {
        Restaurant item = repository.getByIdAndUserId(restaurant.getId(), userId);
        if (!restaurant.isNew() && item == null) {
            return checkNotFoundWithId(item, restaurant.getId());
        }
        restaurant.setUser(userRepository.getOne(userId));
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public List<Restaurant> getAllByUser(int userId) {
        return repository.getByUserIdOrderByName(userId);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }
}
