package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.repository.MenuRepository;
import com.klyashtorny.graduation.repository.RestaurantRepository;
import com.klyashtorny.graduation.util.DateTimeUtil;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.klyashtorny.graduation.util.ValidationUtil.*;

@Repository
public class MenuServiceImpl implements MenuService {

    private final MenuRepository repository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Menu> getAllByRestaurant(int restaurantId) {
        return repository.getByRestaurantIdOrderByName(restaurantId);
    }

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(menu);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteByIdAndRestaurantId(id, restaurantId)!=0, id);
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId (repository.getByIdAndRestaurantId(id, restaurantId), id);
    }

    @Override
    public Menu getActualWithDishes(int id, int restaurantId, LocalDate date) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithDishes(id, restaurantId, date).orElse(null), id);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        Menu item = repository.getByIdAndRestaurantId(menu.getId(), restaurantId);
        if (!menu.isNew() && item == null) {
            return checkNotFoundWithId(item, menu.getId());
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return checkNotFoundWithId(repository.save(menu), menu.getId());
    }
}
