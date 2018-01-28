package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Dish;
import com.klyashtorny.graduation.util.exception.ErrorType;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.klyashtorny.graduation.DishTestData.*;
import static com.klyashtorny.graduation.DishTestData.getCreated;
import static com.klyashtorny.graduation.DishTestData.getUpdated;
import static com.klyashtorny.graduation.MenuTestData.*;
import static org.hamcrest.core.StringContains.containsString;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService dishService;

    @Test
    public void testGetAllByMenu() throws Exception {
        List<Dish> all = dishService.getAllByMenu(MENU_ID);
        assertMatch(all, DISHES_ASTORIA);
    }

    @Test
    public void get() throws Exception {
        Dish menu = dishService.get(DISH_ID, MENU_ID);
        assertMatch(menu, DISH_1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        dishService.get(DISH_ID, 1);
    }

    @Test
    public void delete() throws Exception {
        dishService.delete(DISH_ID, MENU_ID);
        assertMatch(dishService.getAllByMenu(MENU_ID), DISH_2, DISH_3);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        dishService.delete(DISH_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Dish created = getCreated();
        dishService.create(created, MENU_ID);
        assertMatch(dishService.getAllByMenu(MENU_ID), DISH_1, DISH_2, created, DISH_3);
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        dishService.update(updated, MENU_ID);
        assertMatch(dishService.get(DISH_ID, MENU_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(DISH_ID)));
        dishService.update(DISH_1, MENU_3.getId());
    }

}
