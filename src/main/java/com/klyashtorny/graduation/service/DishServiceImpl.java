package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Dish;
import com.klyashtorny.graduation.repository.DishRepository;
import com.klyashtorny.graduation.repository.MenuRepository;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.klyashtorny.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    private final MenuRepository menuRepository;

    @Autowired
    public DishServiceImpl(DishRepository repository, MenuRepository menuRepository) {
        this.repository = repository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setMenu(menuRepository.getOne(menuId));
        return repository.save(dish);
    }

    @Override
    public void delete(int id, int menuId) throws NotFoundException {
        checkNotFoundWithId(repository.deleteByIdAndMenuId(id, menuId)!=0, id);
    }

    @Override
    public Dish get(int id, int menuId) throws NotFoundException {
        return checkNotFoundWithId(repository.getByIdAndMenuId(id, menuId), id);
    }

    @Override
    public Dish update(Dish dish, int menuId) throws NotFoundException {
        Dish item = repository.getByIdAndMenuId(dish.getId(), menuId);
        if (!dish.isNew() && item == null) {
            return checkNotFoundWithId(item, dish.getId());
        }
        dish.setMenu(menuRepository.getOne(menuId));
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @Override
    public List<Dish> getAllByMenu(int menuId) {
        return repository.getByMenuIdOrderByName(menuId);
    }
}
