package com.klyashtorny.graduation.service;

import com.klyashtorny.graduation.model.Menu;
import com.klyashtorny.graduation.util.exception.ErrorType;
import com.klyashtorny.graduation.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.klyashtorny.graduation.DishTestData.*;
import static com.klyashtorny.graduation.MenuTestData.getCreated;
import static com.klyashtorny.graduation.MenuTestData.getUpdated;
import static com.klyashtorny.graduation.MenuTestData.*;
import static com.klyashtorny.graduation.RestaurantTestData.RESTAURANT_3;
import static com.klyashtorny.graduation.RestaurantTestData.RESTAURANT_ID;
import static com.klyashtorny.graduation.util.DateTimeUtil.today;
import static org.hamcrest.core.StringContains.containsString;


public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuService menuService;

    @Test
    public void get() throws Exception {
        Menu menu = menuService.get(MENU_ID, RESTAURANT_ID);
        assertMatch(menu, MENU_1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        menuService.get(MENU_ID, 1);
    }

    @Test
    public void getActualWithDishes() throws Exception {
        Menu menu = menuService.getActualWithDishes(RESTAURANT_ID, today());
        assertMatch(menu, MENU_1);
        assertMatch(menu.getDishes(), DISHES_ASTORIA);
    }


    @Test
    public void getActualWithDishesNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        menuService.getActualWithDishes(RESTAURANT_3.getId(), today());
    }

    @Test
    public void testGetAllByRestaurant() throws Exception {
        List<Menu> all = menuService.getAllByRestaurant(RESTAURANT_ID);
        assertMatch(all, MENU_1, MENU_2);
    }

    @Test
    public void delete() throws Exception {
        menuService.delete(MENU_ID, RESTAURANT_ID);
        assertMatch(menuService.getAllByRestaurant(RESTAURANT_ID), MENU_2);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        menuService.delete(MENU_ID, 1);
    }

    @Test
    public void create() throws Exception {
        Menu created = getCreated();
        menuService.create(created, RESTAURANT_ID);
        assertMatch(menuService.getAllByRestaurant(RESTAURANT_ID), MENU_1, MENU_2, created);
    }

    @Test
    public void update() throws Exception {
        Menu updated = getUpdated();
        menuService.update(updated, RESTAURANT_ID);
        assertMatch(menuService.get(MENU_ID, RESTAURANT_ID), updated);
    }

    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(containsString(ErrorType.DATA_NOT_FOUND.name()));
        thrown.expectMessage(containsString(NotFoundException.NOT_FOUND_EXCEPTION));
        thrown.expectMessage(containsString(String.valueOf(MENU_ID)));
        menuService.update(MENU_1, RESTAURANT_3.getId());
    }

}
